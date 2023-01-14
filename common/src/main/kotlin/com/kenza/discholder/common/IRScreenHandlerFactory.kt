package com.kenza.discholder.common

import com.kenza.discholder.render.DiscHolderBlockEntityGuiDescription
import dev.architectury.registry.menu.ExtendedMenuProvider
import dev.architectury.registry.menu.MenuRegistry
import io.netty.buffer.Unpooled
import literal
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.network.PacketByteBuf
import net.minecraft.screen.NamedScreenHandlerFactory
import net.minecraft.screen.ScreenHandler
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.text.Text
import net.minecraft.util.math.BlockPos
import java.lang.ref.WeakReference

open class ScreenHandlerFactory(
    private val handlerFactory: (Int, PlayerInventory, ScreenHandlerContext) -> DiscHolderBlockEntityGuiDescription,
    private val pos: BlockPos
) : ExtendedMenuProvider, NamedScreenHandlerFactory {

    var playerRef : WeakReference<PlayerEntity>? = null


    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): ScreenHandler? {
        return handlerFactory(syncId, inv, ScreenHandlerContext.create(inv.player.world, pos))
    }

    //    override fun createMenu(syncId: Int, inv: PlayerInventory, player: PlayerEntity?): ScreenHandler {
//        playerRef = WeakReference(player)
//        return handlerFactory(syncId, inv, ScreenHandlerContext.create(inv.player.world, pos))
//    }
//
////    override fun writeScreenOpeningData(player: ServerPlayerEntity, buf: PacketByteBuf) {
////        buf.writeBlockPos(pos)
////    }
//
    override fun getDisplayName(): Text? = Text.of("")

    override fun saveExtraData(buf: PacketByteBuf?) {
        buf?.writeBlockPos(pos)
    }
//
//    override fun saveExtraData(buf: PacketByteBuf?) {
//        buf?.writeBlockPos(pos)
//    }



}