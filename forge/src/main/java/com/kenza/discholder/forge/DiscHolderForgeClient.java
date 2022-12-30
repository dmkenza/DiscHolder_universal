package com.kenza.discholder.forge;

import com.kenza.discholder.registry.ModRegistriesClient;
import dev.architectury.registry.client.rendering.ColorHandlerRegistry;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
//
import static com.kenza.discholder.RefKt.MOD_ID;

//
@Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
//@Mod.EventBusSubscriber(modid = MOD_ID, bus = MOD)
public class DiscHolderForgeClient {

//    @OnlyIn(Dist.DEDICATED_SERVER)
    @SubscribeEvent
    public static void init(FMLClientSetupEvent event) {


//        LibGuiClient.onInitializeClient();
//        ColorHandlerRegistry
//        BackgroundPainter.class.arrayType();

//        NinePatch.
//        BackgroundPainter x = BackgroundPainter.SLOT;

//        CottonHud.add(new WHudTest(), 10, -20, 10, 10);
//        CottonHud.add(new WLabel(Text.literal("Test label")), 10, -30, 10, 10);
////
        ModRegistriesClient.INSTANCE.onInit();
    }

}


