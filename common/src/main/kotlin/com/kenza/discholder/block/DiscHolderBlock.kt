package com.kenza.discholder.block

import com.kenza.discholder.common.ScreenHandlerFactory
import com.kenza.discholder.entity.DiscHolderBlockEntity
import com.kenza.discholder.registry.ModRegistries
import com.kenza.discholder.registry.ModRegistriesClient.testScreenFactory
import com.kenza.discholder.render.DiscHolderBlockEntityGuiDescription
import com.kenza.discholder.utils.getSlotInBlock
import com.kenza.discholder.utils.hasMusicDiscItemType
import dev.architectury.registry.menu.MenuRegistry
import io.kenza.support.utils.isRenderThread
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.block.entity.BlockEntityTicker
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.ItemPlacementContext
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.state.StateManager
import net.minecraft.state.property.DirectionProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.BlockRotation
import net.minecraft.util.Hand
import net.minecraft.util.ItemScatterer
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

class DiscHolderBlock(
    val blockId: String,
    settings: Settings?,
    val screenHandler: ((Int, PlayerInventory, ScreenHandlerContext) -> DiscHolderBlockEntityGuiDescription),
) : BlockWithEntity(settings) {


    val SHAPE: VoxelShape = createCuboidShape(0.01, 0.01, .01, 16.0, 6.0, 16.0)


    override fun getOutlineShape(
        state: BlockState,
        view: BlockView,
        pos: BlockPos?,
        context: ShapeContext?,
    ): VoxelShape {
        return SHAPE
    }

    override fun getPlacementState(ctx: ItemPlacementContext?): BlockState? {
        super.getPlacementState(ctx)
        return this.defaultState.with(HORIZONTAL_FACING, ctx?.playerFacing?.opposite)//.with(ACTIVE, false)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>?) {
        super.appendProperties(builder)
        builder?.add(HORIZONTAL_FACING)
    }

    override fun rotate(state: BlockState, rotation: BlockRotation): BlockState {
        return state.with(HORIZONTAL_FACING, getRotated(state[HORIZONTAL_FACING], rotation))
    }


    override fun <T : BlockEntity?> getTicker(
        world: World,
        state: BlockState?,
        type: BlockEntityType<T>?,
    ): BlockEntityTicker<T>? {
        return BlockEntityTicker { _, _, _, blockEntity -> (blockEntity as? DiscHolderBlockEntity)?.tick() }
    }

    @Suppress("DEPRECATION")
    override fun onStateReplaced(state: BlockState, world: World, pos: BlockPos, newState: BlockState, moved: Boolean) {
        val oldBlockEntity = world.getBlockEntity(pos) as? DiscHolderBlockEntity
        super.onStateReplaced(state, world, pos, newState, moved)

        if (oldBlockEntity?.items?.isNotEmpty() == true) {
            ItemScatterer.spawn(world, pos, oldBlockEntity.items)
            world.updateComparators(pos, this)
        }
    }

    override fun getRenderType(state: BlockState): BlockRenderType {
        return BlockRenderType.MODEL
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hitResult: BlockHitResult,
    ): ActionResult {
        if (world.isClient) return ActionResult.SUCCESS
//            if (!world.isClient) {
        val vec3d: Vec3d = hitResult.pos
        val facing: Direction = state[HORIZONTAL_FACING]
        var inc = if (facing === Direction.NORTH || facing === Direction.SOUTH) vec3d.x % 1 else vec3d.z % 1
        if(inc <0){
            inc += 1
        }

        val slot: Int = getSlotInBlock(inc)
        val blockEntity: DiscHolderBlockEntity? = world.getBlockEntity(pos) as? DiscHolderBlockEntity

        val itemStackInSlot = blockEntity?.items?.getOrNull(slot)

        if (slot != -1) {

            if (itemStackInSlot?.isEmpty == true && player.mainHandStack.hasMusicDiscItemType()) {
                blockEntity.items.set(slot, player.mainHandStack.copy())
                player.mainHandStack.decrement(1)
                blockEntity.markDirty()
            } else if (itemStackInSlot.hasMusicDiscItemType()) {

//                    val itemEntity = ItemEntity(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), itemStack)
                player.inventory.offerOrDrop(itemStackInSlot);
                itemStackInSlot?.decrement(1)
//                    if (!player.giveItemStack(itemStack)) {
//                        player.dropItem(itemStack?.copy(), false)
//                        itemStack?.decrement(1)
//                    }

//                    world.spawnEntity(itemEntity)
            } else {
                screenHandler?.let {
//                    MenuRegistry.openExtendedMenu(player, testScreenFactory())
//                    player.openHandledScreen(state.createScreenHandlerFactory(world, pos))

//                    player.openHandledScreen(IRScreenHandlerFactory(screenHandler, pos))
//                    player.openHandledScreen(ScreenHandlerFactory(screenHandler, pos))
//                    if(!isRenderThread()){
//                        MenuRegistry.openExtendedMenu(player as ServerPlayerEntity ,ScreenHandlerFactory(screenHandler, pos))
//                    }

                    (player as? ServerPlayerEntity)?.let {
                        MenuRegistry.openExtendedMenu(player, ScreenHandlerFactory(screenHandler, pos))
                    }
//                    player.openHandledScreen(ScreenHandlerFactory(screenHandler, pos))
                }

            }

//            ItemScatterer.spawn(world, pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble(), player.mainHandStack)
        }else{
            (player as? ServerPlayerEntity)?.let {
                MenuRegistry.openExtendedMenu(player, ScreenHandlerFactory(screenHandler, pos))
            }
        }
        return ActionResult.SUCCESS
    }





    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
//        val keyType = this.translationKey.split(".").lastOrNull()
        val type = ModRegistries.MOD_ENTITY_TYPES_MAP.get(blockId)?.get()!!
        return DiscHolderBlockEntity(blockId, type, pos, state)
    }


    companion object {
        val HORIZONTAL_FACING: DirectionProperty = Properties.HORIZONTAL_FACING

        fun getRotated(direction: Direction, rotation: BlockRotation): Direction =
            if (direction.axis.isVertical) direction else when (rotation) {
                BlockRotation.NONE -> direction
                BlockRotation.CLOCKWISE_90 -> direction.rotateYClockwise()
                BlockRotation.CLOCKWISE_180 -> direction.opposite
                BlockRotation.COUNTERCLOCKWISE_90 -> direction.rotateYCounterclockwise()
            }
    }
}

