package com.kenza.discholder;

import com.kenza.discholder.entity.DiscHolderBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public interface CommonPlatformHelper1 {

    public ItemGroup registerCreativeModeTab(Identifier name, Supplier<ItemStack> icon);

//    public Supplier<BlockEntityType<DiscHolderBlockEntity>> registerBlockEntityType(String name);

}
