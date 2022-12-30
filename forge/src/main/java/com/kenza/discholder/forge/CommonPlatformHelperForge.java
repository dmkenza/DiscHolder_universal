package com.kenza.discholder.forge;

import com.kenza.discholder.CommonPlatformHelper1;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class CommonPlatformHelperForge implements CommonPlatformHelper1 {

//    public static final DeferredRegister<VillagerProfession> PROFESSIONS = DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, MOD_ID);
//    public static final DeferredRegister<PointOfInterestType> POI_TYPES = DeferredRegister.create(ForgeRegistries.POI_TYPES, MOD_ID);

    public ItemGroup registerCreativeModeTab(Identifier name, Supplier<ItemStack> icon) {
        return new ItemGroup(name.getNamespace()) {
            @Override
            public ItemStack createIcon() {
                return icon.get();
            }
        };
    }

//    public Supplier<VillagerProfession> registerProfession(String name, Supplier<VillagerProfession> profession) {
//        return PROFESSIONS.register(name, profession);
//    }
//
//    public Supplier<PointOfInterestType> registerPoiType(String name, Supplier<PointOfInterestType> poiType) {
//        return POI_TYPES.register(name, poiType);
//    }


}
