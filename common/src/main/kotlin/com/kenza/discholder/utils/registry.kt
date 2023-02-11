package com.kenza.discholder.utils

import com.kenza.discholder.commonPlatformHelper
import dev.architectury.registry.registries.RegistrySupplier
import net.minecraft.block.Block
import net.minecraft.util.Identifier
import net.minecraft.util.registry.RegistryKey
import net.minecraft.village.VillagerProfession
import net.minecraft.world.poi.PointOfInterestType
import java.util.function.Supplier

//fun Identifier.poiTypeFabric(supplier: Supplier<Block>): Supplier<PointOfInterestType> {
//    return commonPlatformHelper.registerPoiType("dj_poi", supplier)
//}
//
//fun Identifier.professionFabric(supplier: RegistryKey<PointOfInterestType>): Supplier<VillagerProfession> {
//    return commonPlatformHelper.registerProfession("dj", supplier)
//}