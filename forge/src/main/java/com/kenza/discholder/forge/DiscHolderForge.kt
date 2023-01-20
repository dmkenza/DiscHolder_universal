package com.kenza.discholder.forge

import com.kenza.discholder.MOD_ID
import com.kenza.discholder.commonPlatformHelper
import com.kenza.discholder.registry.ModRegistries.onInit
import com.kenza.discholder.registry.ModRegistries.onSetupEvent
import dev.architectury.platform.forge.EventBuses
import io.github.cottonmc.cotton.gui.impl.LibGuiCommon
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.MOD_CONTEXT

//import dev.architectury.platform.forge.EventBuses;
@Mod(MOD_ID)
class DiscHolderForge {
    //    public static final RegistryObject<BlockEntityType<BuilderBlockEntity>> BUILDER_BLOCK_ENTITY =
    //            BLOCK_ENTITIES.register("builder_block_entity", () ->
    //            {
    //                return Builder.of(BuilderBlockEntity::new, ModBlocks.BUILDER_BLOCK.get()).build(null);
    //            });
    init {

//        MoreDiscs.MOD_ID
        commonPlatformHelper = CommonPlatformHelperForge()

        // Submit our event bus to let architectury register our content on the right time
        val bus = MOD_CONTEXT.getKEventBus()
        //        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MOD_ID, bus)
        bus.addListener { event: FMLCommonSetupEvent -> setup(event) }
        bus.addListener { event: FMLClientSetupEvent -> DiscHolderForgeClient.setup(event) }
        LibGuiCommon.onInitialize()
        onInit()
        MinecraftForge.EVENT_BUS.register(this)

//        DistExecutor.safeRunWhenOn(Dist.CLIENT) {
//            DistExecutor.SafeRunnable(
//                DiscHolderForgeClient::init
//            )
//        }


//        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> DiscHolderForgeClient.init());
    }

    //    private DistExecutor.SafeRunnable test1() {
    //    }
    private fun setup(event: FMLCommonSetupEvent) {
        event.enqueueWork { onSetupEvent(event) }
    }

    companion object {
        val BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID)
    }
}