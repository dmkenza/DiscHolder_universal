package com.kenza.discholder

import com.kenza.discholder.entity.DiscHolderBlockEntity
import com.kenza.discholder.registry.ModRegistries
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.datafixer.TypeReferences
import net.minecraft.util.Util
import net.minecraft.util.math.BlockPos
import java.util.function.Supplier


object FabricSpecificImpl : FabricSpecific {


//    fun createBlockEntity(
//        itemId: String,
//    ): Supplier<BlockEntityType<DiscHolderBlockEntity>> {
//
////        var type: Supplier<BlockEntityType<*>>? = null
//        var type: Supplier<BlockEntityType<DiscHolderBlockEntity>>? = null
////        var type1 = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, itemId)
//        type = Supplier {
//            BlockEntityType.Builder.create(
////                FabricBlockEntityTypeBuilder.create(
//                { pos: BlockPos, state: BlockState ->
//                    DiscHolderBlockEntity(
//                        type!!.get(),
//                        pos,
//                        state
//                    )
//                }, ModRegistries.MOD_BLOCKS_MAP[itemId]?.get()
//            )
//                .build(null)
//        }
////        var type1: BlockEntityType<DiscHolderBlockEntity>? = null
//
//
//
//        return type
//    }


    override fun test1() {
//        Registry.register(Registry.BLOCK_ENTITY_TYPE, identifier(itemId), type.get())
    }

//    val ENTITY_VILLAGER_WORK_CLOUD_TINKER = registerSoundEvent("profession.cloud_tinker")

//    val MOD_DJ_POI = CommonPlatformHelperFabric.registerPoiType1("dj") {
//        PointOfInterestType(PoiTypesInvoker.invokeGetBlockStates(Blocks.JUKEBOX ), 1, 1)
//    }
//
//    val MOD_DJ_PROFESSION = CommonPlatformHelperFabric.registerProfession1(
//        "dj", {
//            VillagerProfession(
//                "dj",
//                { holder: RegistryEntry<PointOfInterestType> -> holder.value() as PointOfInterestType == MOD_DJ_POI.get() },
//                { holder: RegistryEntry<PointOfInterestType> -> holder.value() as PointOfInterestType == MOD_DJ_POI.get() },
//                ImmutableSet.of(),
//                ImmutableSet.of(),
//                ModRegistries.MOD_SOUND_DJ_POI.get()
//            )
//        }
//    )

    fun initData() {

//        ModProfessions.onInit(MOD_DJ_PROFESSION)


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