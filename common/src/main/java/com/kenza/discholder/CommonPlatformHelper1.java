package com.kenza.discholder;

import dev.architectury.registry.CreativeTabRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

import static com.kenza.discholder.RefKt.MOD_ID;

public interface CommonPlatformHelper1 {

    public static CreativeTabRegistry.TabSupplier registerCreativeModeTab(Identifier name, Supplier<ItemStack> icon) {
        return  CreativeTabRegistry.create(new Identifier(MOD_ID, name.getPath()), icon);
    }

//    public Supplier<BlockEntityType<DiscHolderBlockEntity>> registerBlockEntityType(String name);

}
