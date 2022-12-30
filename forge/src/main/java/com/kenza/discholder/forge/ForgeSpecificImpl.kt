package com.kenza.discholder.forge

import com.kenza.discholder.registry.ModProfession.buyMusicForEmeraldsFactory
import com.kenza.discholder.registry.ModRegistries.MOD_DJ_PROFESSION
import dev.architectury.registry.level.entity.trade.TradeRegistry


object ForgeSpecificImpl {


    fun initData(){

//        ModProfessions.onInit(MOD_DJ_PROFESSION)
//        registerTrades()

        TradeRegistry.registerVillagerTrade(
            MOD_DJ_PROFESSION.get(), 1,
         { entity, random ->
             println("Trade1")
             buyMusicForEmeraldsFactory.create(entity, random)
//            TradeOffer(
//                ItemStack(Items.EMERALD, random.nextBetween(24, 36)),
//                ItemStack(Items.GUNPOWDER, random.nextBetween(3, 5)),
//                5, 10, 0.02f
//            )
        })



//        TradeOfferHelper.registerVillagerOffers(
//            MOD_DJ_PROFESSION.get(), 1
//        ) { factories ->
////            factories.add { entity, random ->
////                TradeOffer(
////                    ItemStack(Items.EMERALD, random.nextBetween(2, 3)),
////                    ItemStack(ModItems.ALUMENTUM_ITEM, random.nextBetween(3, 5)),
////                    20, 5, 0.02f
////                )
////            }
//
//
    }
}