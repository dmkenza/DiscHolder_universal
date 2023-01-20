package com.kenza.discholder.registry

import com.kenza.discholder.entity.DiscHolderBlockEntity
import com.kenza.discholder.registry.ModRegistries.DISC_BLOCKENTITY_GUI_HANDLER_TYPE
import com.kenza.discholder.registry.ModRegistries.MOD_ENTITY_TYPES_MAP
import com.kenza.discholder.render.DiscHolderBlockEntityGuiDescription
import com.kenza.discholder.render.DiscHolderBlockEntityRenderer
import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry
import dev.architectury.registry.menu.MenuRegistry
import io.github.cottonmc.cotton.gui.client.CottonInventoryScreen
import io.github.cottonmc.cotton.gui.impl.client.LibGuiClient
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.text.Text

object ModRegistriesClient {


    lateinit var testScreenFactory: (DiscHolderBlockEntityGuiDescription, PlayerInventory?, Text?) -> CottonInventoryScreen<DiscHolderBlockEntityGuiDescription>

    fun onInit() {

        ModNet.registerClient()

        MOD_ENTITY_TYPES_MAP
            .values
            .map { it.get() }
            .filterIsInstance<BlockEntityType<DiscHolderBlockEntity>>()
            .map { type ->
                BlockEntityRendererRegistry.register(type) {
                    DiscHolderBlockEntityRenderer()
                }
            }

        testScreenFactory =
            { description: DiscHolderBlockEntityGuiDescription, inventory: PlayerInventory?, title: Text? ->
                CottonInventoryScreen(
                    description,
                    inventory,
                    title
                )
            }

//        MenuRegistry.re

        MenuRegistry.registerScreenFactory<
                DiscHolderBlockEntityGuiDescription, CottonInventoryScreen<DiscHolderBlockEntityGuiDescription>>(
            DISC_BLOCKENTITY_GUI_HANDLER_TYPE, testScreenFactory
        )

    }
}