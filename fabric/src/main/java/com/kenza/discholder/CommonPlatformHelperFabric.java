package com.kenza.discholder;

import com.kenza.discholder.entity.DiscHolderBlockEntity;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class CommonPlatformHelperFabric implements CommonPlatformHelper1 {


//    public static void initProfessionData(){
//        PoiTypesInvoker.invokeGetBlockStates((Block) Blocks.JUKEBOX).forEach((state) -> {
//            PoiTypesInvoker.getTypeByState().put(state,
//                    Registry.POINT_OF_INTEREST_TYPE.getEntry(RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY,
//                            new Identifier(MOD_ID, "dj"))).get());
//        });
//    }
//
//
//    public static Supplier<PointOfInterestType> registerPoiType1(String name, Supplier<PointOfInterestType> poiType) {
//        RegistryKey<PointOfInterestType> resourceKey = RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(MOD_ID, name));
//        PointOfInterestType registry = (PointOfInterestType)Registry.register(Registry.POINT_OF_INTEREST_TYPE, resourceKey, (PointOfInterestType)poiType.get());
//        PoiTypesInvoker.invokeRegisterBlockStates(Registry.POINT_OF_INTEREST_TYPE.entryOf(resourceKey));
//        return () -> {
//            return registry;
//        };
//    }
//
//    public static Supplier<VillagerProfession> registerProfession1(String name, Supplier<VillagerProfession> profession) {
//        VillagerProfession registry = (VillagerProfession)Registry.register(Registry.VILLAGER_PROFESSION, new Identifier(MOD_ID, name), (VillagerProfession)profession.get());
//        return () -> {
//            return registry;
//        };
//    }
//
//
//    public Supplier<VillagerProfession> registerProfession(String name, RegistryKey<PointOfInterestType> poi) {
//        return  () -> Registry.register(
//                Registry.VILLAGER_PROFESSION, new Identifier(MOD_ID, name),
//                VillagerProfessionBuilder.create().id(new Identifier(MOD_ID, name)).workstation(poi).workSound(
//                        MOD_SOUND_DJ_POI.get()
//                ).build()
//        );
//    }
//
//    public Supplier<PointOfInterestType> registerPoiType(
//            String name,
//            Supplier<Block> block
//    ) {
//        return () -> PointOfInterestHelper.register(
//                new Identifier(MOD_ID, name),
//                1, 1, ImmutableSet.copyOf(block.get().getStateManager().getStates())
//        );
////        RegistryKey<PointOfInterestType> resourceKey = RegistryKey.of(Registry.POINT_OF_INTEREST_TYPE_KEY, new Identifier(MOD_ID, name));
////        PointOfInterestType registry = (PointOfInterestType)Registry.register(Registry.POINT_OF_INTEREST_TYPE, resourceKey, (PointOfInterestType)poiType.get());
////        PoiTypesInvoker.invokeRegisterBlockStates(Registry.POINT_OF_INTEREST_TYPE.entryOf(resourceKey));
////        return () -> {
////            return registry;
////        };
//    }

    public ItemGroup registerCreativeModeTab(Identifier name, Supplier<ItemStack> icon) {
        return FabricItemGroupBuilder.build(name, icon);
    }

//    @Override
//    public Supplier<BlockEntityType<DiscHolderBlockEntity>> registerBlockEntityType(String name) {
//         return  FabricSpecificImpl.INSTANCE.createBlockEntity(name);
//    }


}
