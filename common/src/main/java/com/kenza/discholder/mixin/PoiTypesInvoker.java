package com.kenza.discholder.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.poi.PointOfInterestType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;
import java.util.Set;

@Mixin(PointOfInterestType.class)
public interface PoiTypesInvoker {

    @Invoker("<init>")
    static PointOfInterestType invokeConstructor(String name, Set<BlockState> matchingStates, int maxTickets, int validRange) {
        throw new AssertionError();
    }

    @Invoker("setup")
    static PointOfInterestType invokeRegisterBlockStates(PointOfInterestType poiType) {
        throw new AssertionError();
    }
}

