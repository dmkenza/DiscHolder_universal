package com.kenza.discholder.registry

import com.kenza.discholder.entity.DiscHolderBlockEntity
import com.kenza.discholder.registry.ModRegistries.UPDATE_INV_PACKET_ID
import dev.architectury.networking.NetworkManager
import net.minecraft.client.MinecraftClient
import net.minecraft.item.ItemStack
import net.minecraft.network.PacketByteBuf
import net.minecraft.util.collection.DefaultedList

object ModNet {

    fun registerClient() {


//        BackgroundPainter.class.arrayType();
        // update DiscHolder to show its inventory
        NetworkManager.registerReceiver(
            NetworkManager.Side.S2C, UPDATE_INV_PACKET_ID
        ) { buf: PacketByteBuf, context: NetworkManager.PacketContext ->

            val client = MinecraftClient.getInstance()

            val pos = buf.readBlockPos()
            val inv = DefaultedList.ofSize(7, ItemStack.EMPTY)
            for (i in 0..6) {
                inv[i] = buf.readItemStack()
            }
            client.execute {
                val blockEntity: DiscHolderBlockEntity? =
                    MinecraftClient.getInstance().world?.getBlockEntity(pos) as DiscHolderBlockEntity?

                inv.mapIndexed { index, itemStack ->
                    blockEntity?.setStack(index, itemStack)
                }
            }
        }

    }
}