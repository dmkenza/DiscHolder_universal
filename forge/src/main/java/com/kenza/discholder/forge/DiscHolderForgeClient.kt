package com.kenza.discholder.forge

import com.kenza.discholder.registry.ModRegistriesClient.onInit
import io.github.cottonmc.cotton.gui.impl.client.LibGuiClient
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent

//
//
//@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//@Mod.EventBusSubscriber(modid = MOD_ID, bus = MOD)
object DiscHolderForgeClient {
    //    @OnlyIn(Dist.DEDICATED_SERVER)
    fun init() {


//        LibGuiClient.onInitializeClient();
//        ColorHandlerRegistry
//        BackgroundPainter.class.arrayType();

//        NinePatch.
//        BackgroundPainter x = BackgroundPainter.SLOT;

//        CottonHud.add(new WHudTest(), 10, -20, 10, 10);
//        CottonHud.add(new WLabel(Text.literal("Test label")), 10, -30, 10, 10);
////

    }

    fun setup(event: FMLClientSetupEvent) {
        LibGuiClient.onInitializeClient()
        onInit()
    }
}