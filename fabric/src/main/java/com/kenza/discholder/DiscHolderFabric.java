package com.kenza.discholder;

import com.kenza.discholder.registry.ModRegistries;
import com.kenza.discholder.render.DiscHolderBlockEntityRenderer;
import net.fabricmc.api.ModInitializer;


public class DiscHolderFabric implements ModInitializer {
    @Override
    public void onInitialize() {

        DiscHolderBlockEntityRenderer.class.arrayType();

        RefKt.commonPlatformHelper = new CommonPlatformHelperFabric();
        ModRegistries.INSTANCE.onInit();
        ModRegistries.INSTANCE.onSetupEvent(new Object());
//        FabricSpecificImpl.INSTANCE.initData();

//                Test.INSTANCE.test();
//        initProfessionData();


    }
}
