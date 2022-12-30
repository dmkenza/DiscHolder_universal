package io.kenza.support.utils

import dev.architectury.registry.registries.DeferredRegister
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.sound.SoundEvent
import net.minecraft.village.VillagerProfession
import net.minecraft.world.poi.PointOfInterestType

object Ref {
    lateinit var ITEMS: DeferredRegister<Item>
    lateinit var SOUNDS_EVENTS: DeferredRegister<SoundEvent>
    lateinit var BLOCKS: DeferredRegister<Block>
    lateinit var BLOCK_ENTITY_TYPES: DeferredRegister<BlockEntityType<*>>
    lateinit var SCREEN_HANDLERS: DeferredRegister<ScreenHandlerType<*>>
    lateinit var POINT_OF_INTEREST_TYPES: DeferredRegister<PointOfInterestType>
    lateinit var VILLAGER_PROFESSIONS: DeferredRegister<VillagerProfession>

}
