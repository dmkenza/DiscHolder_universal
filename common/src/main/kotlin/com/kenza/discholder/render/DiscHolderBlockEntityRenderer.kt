package com.kenza.discholder.render

import com.kenza.discholder.entity.DiscHolderBlockEntity
import com.kenza.discholder.utils.getSlotInBlock
import io.kenza.support.utils.toVec3d
import io.kenza.support.utils.value
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderLayers
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.model.json.ModelTransformation
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.item.MusicDiscItem
import net.minecraft.state.property.Properties
import net.minecraft.text.MutableText
import net.minecraft.util.hit.HitResult
import net.minecraft.util.math.Direction
import net.minecraft.util.math.Matrix4f
import net.minecraft.util.math.Quaternion
import net.minecraft.util.math.Vec3f
import kotlin.math.absoluteValue

class DiscHolderBlockEntityRenderer : BlockEntityRenderer<DiscHolderBlockEntity> {

    private val mc: MinecraftClient
        get() = MinecraftClient.getInstance()

    override fun render(
        entity: DiscHolderBlockEntity,
        tickDelta: Float,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int,
    ) {
        val player = mc.player ?: return

        player.mainHandStack ?: return


        val blockState = MinecraftClient.getInstance().world?.getBlockState(entity.pos) ?: return

        val facing: Direction = blockState.getOrEmpty(Properties.HORIZONTAL_FACING).value ?: return

        val isXAxis = facing.axis === Direction.Axis.X
        for (i in 0..6) {
//            val item: ItemStack = player.mainHandStack //entity.records.getStackInSlot(i)
            val itemStack: ItemStack = entity.getDiscInSlot(i)

            if (itemStack.isEmpty) continue

            val discItem = (itemStack.item as? MusicDiscItem)

            val slot = getShownSlot(entity, facing)

            if (slot == i && discItem != null) {
                val text = discItem.description.formatted()
                renderText(matrices, vertexConsumers, slot, text, isXAxis, light)
            }


            val shiftX = if (isXAxis) .5 - .03125 else .125 + .125 * i
            val shiftY = .375
            val shiftZ = if (isXAxis) .125 + .125 * i else .5 + .03125

            matrices.push()
            matrices.translate(shiftX, shiftY, shiftZ)

            if (!isXAxis) matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90f))


            MinecraftClient.getInstance().itemRenderer.renderItem(
                itemStack,
                ModelTransformation.Mode.FIXED,
                light,
                overlay,
                matrices,
                vertexConsumers,
                -1000
            )

            matrices.pop()


        }


    }

    private fun getShownSlot(
        entity: DiscHolderBlockEntity,
        facing: Direction,
    ): Int {
        val target = mc.crosshairTarget ?: return -1
        return if (target.type == HitResult.Type.BLOCK && target.pos.isInRange(
                entity.pos.toVec3d().add(0.5, 0.5, 0.5), 0.5
            )
        ) {
            var inc =
                if (facing === Direction.NORTH || facing === Direction.SOUTH) target.pos.x % 1 else target.pos.z % 1
            if (inc < 0) {
                inc += 1
            }
            getSlotInBlock(inc)
        } else {
            -1
        }
    }

    private fun renderText(
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        slot: Int,
        text: MutableText,
        isXAxis: Boolean,
        light: Int,
    ) {

        matrices.push()

        val shiftX = if (isXAxis) .5 - .03125 else .125 + .125 * slot
        val shiftY = .375
        val shiftZ = if (isXAxis) .125 + .125 * slot else .5 + .03125

        val renderManager = MinecraftClient.getInstance().entityRenderDispatcher

        matrices.translate(shiftX, shiftY + .7f, shiftZ)


        val rotation: Quaternion = renderManager.camera.getRotation().copy()
        rotation.scale(-1.0f)
        matrices.multiply(rotation)

        matrices.scale(-0.025f, -0.025f, 0.025f)


        val offset = (-mc.textRenderer.getWidth(text) / 2).toFloat()

        val modelViewMatrix: Matrix4f = matrices.peek().positionMatrix

//        val x1 = vertexConsumers.getBuffer(RenderLayer.getCutout())
//        vertexConsumers.getBuffer(RenderLayers.getBlockLayer(entity.cachedState)),


        /** extra render text for Iris mod fix transparent problem */
        mc.textRenderer.draw(
            matrices,
            text,
            offset,
            0f,
            553648127
        )

        mc.textRenderer.draw(
            text,
            offset,
            0f,
            553648127,
            false,
            modelViewMatrix,
            vertexConsumers,
            true,
            1056964608,
            light
        )
//
        mc.textRenderer.draw(text, offset, 0f, -1, false, modelViewMatrix, vertexConsumers, true, 0, light)

        matrices.pop()
    }
}
