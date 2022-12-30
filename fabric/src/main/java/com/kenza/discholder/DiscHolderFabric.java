package com.kenza.discholder;

import com.kenza.discholder.registry.ModRegistries;
import net.fabricmc.api.ModInitializer;


public class DiscHolderFabric implements ModInitializer {
    @Override
    public void onInitialize() {

        RefKt.commonPlatformHelper = new CommonPlatformHelperFabric();
        ModRegistries.INSTANCE.onInit();
        ModRegistries.INSTANCE.onSetupEvent(new Object());
//        FabricSpecificImpl.INSTANCE.initData();

//                Test.INSTANCE.test();
//        initProfessionData();


    }
}
