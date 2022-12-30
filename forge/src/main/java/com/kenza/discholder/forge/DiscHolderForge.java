package com.kenza.discholder.forge;


import com.kenza.discholder.RefKt;
import com.kenza.discholder.registry.ModNet;
import com.kenza.discholder.registry.ModProfession;
import com.kenza.discholder.registry.ModRegistries;
//import dev.architectury.platform.forge.EventBuses;
import com.kenza.discholder.mixin.PoiTypesInvoker;
import com.samebutdifferent.morevillagers.MoreVillagers;
import com.samebutdifferent.morevillagers.registry.MVBlocks;
import dev.architectury.platform.forge.EventBuses;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;

import static com.kenza.discholder.RefKt.MOD_ID;

@Mod(MOD_ID)
public class DiscHolderForge {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MOD_ID);

//    public static final RegistryObject<BlockEntityType<BuilderBlockEntity>> BUILDER_BLOCK_ENTITY =
//            BLOCK_ENTITIES.register("builder_block_entity", () ->
//            {
//                return Builder.of(BuilderBlockEntity::new, ModBlocks.BUILDER_BLOCK.get()).build(null);
//            });


    public DiscHolderForge() {

//        MoreDiscs.MOD_ID
        RefKt.commonPlatformHelper = new CommonPlatformHelperForge();

        // Submit our event bus to let architectury register our content on the right time
//        IEventBus bus = thedarkcolour.kotlinforforge.forge.ForgeKt.getMOD_CONTEXT().getKEventBus();
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(MOD_ID, bus);
        bus.addListener(this::setup);


        ModRegistries.INSTANCE.onInit();
        MinecraftForge.EVENT_BUS.register(this);


//        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> DiscHolderForgeClient.init());

    }

//    private DistExecutor.SafeRunnable test1() {
//    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModRegistries.INSTANCE.onSetupEvent(event);
//            ForgeSpecificImpl.INSTANCE.initData();


        });
    }


}
