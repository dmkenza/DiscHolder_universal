package com.kenza.discholder.registry

import com.kenza.discholder.mixin.PoiTypesInvoker
import com.kenza.discholder.profession.BuyMusicForEmeraldsFactory
import com.kenza.discholder.profession.SellMusicForEmeraldsFactory
import com.kenza.discholder.utils.identifier
import dev.architectury.registry.level.entity.trade.TradeRegistry
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.village.TradeOffers

object ModProfession {

    val buyMusicForEmeraldsFactory = BuyMusicForEmeraldsFactory(4, 8);
    val sellMusicForEmeraldsFactory = SellMusicForEmeraldsFactory(10, 5)

    private val tradesMap = hashMapOf<Int, List<TradeOffers.Factory>>(
        1 to listOf(buyMusicForEmeraldsFactory, sellMusicForEmeraldsFactory ),
        2 to listOf(sellMusicForEmeraldsFactory, sellMusicForEmeraldsFactory ),
        3 to listOf(sellMusicForEmeraldsFactory, sellMusicForEmeraldsFactory ),
        4 to listOf(sellMusicForEmeraldsFactory, sellMusicForEmeraldsFactory ),
        5 to listOf(sellMusicForEmeraldsFactory, sellMusicForEmeraldsFactory ),
        6 to listOf(sellMusicForEmeraldsFactory, sellMusicForEmeraldsFactory ),
    )
    fun registerBlockStates() {
        PoiTypesInvoker.invokeGetBlockStates(Blocks.JUKEBOX).forEach { state: BlockState? ->
            PoiTypesInvoker.getTypeByState()[state] = Registries.POINT_OF_INTEREST_TYPE.getEntry(
                RegistryKey.of(
                    RegistryKeys.POINT_OF_INTEREST_TYPE,
                    identifier("dj")
                )
            ).get()
        }

//        PoiTypesInvoker.invokeGetBlockStates(MVBlocks.OCEANOGRAPHY_TABLE.get()).forEach(Consumer { state: BlockState? ->
//            PoiTypesInvoker.getTypeByState()[state] = BuiltInRegistries.POINT_OF_INTEREST_TYPE.getHolder(
//                ResourceKey.create(
//                    Registries.POINT_OF_INTEREST_TYPE,
//                    ResourceLocation(MoreVillagers.MOD_ID, "oceanography_table")
//                )
//            ).get()
//        })

    }
    fun registerTrades() {
        tradesMap.map { (lvl, factories) ->
            TradeRegistry.registerVillagerTrade(
                ModRegistries.MOD_DJ_PROFESSION.get(), lvl,
                * factories.toTypedArray()
            )
        }
    }
}