package com.kenza.discholder.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.MerchantScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.kenza.discholder.registry.ModRegistries.MOD_MUSIC_DISC_EMPTY;
import static com.kenza.discholder.utils.UtilsKt.hasMusicDiscItemType;

@Mixin(MerchantScreenHandler.class)
class MerchantScreenHandlerMixin {
//
//    @Inject(method = "autofill(ILnet/minecraft/item/ItemStack;)V",
//            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;canCombine(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z"))


    boolean musicDiscGetted = false;

    @Redirect(method = "autofill(ILnet/minecraft/item/ItemStack;)V",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;canCombine(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z"))
    public boolean kenza_autofill(ItemStack stack, ItemStack otherStack) {

        boolean isEmptyMusicDisc = stack.getItem() == MOD_MUSIC_DISC_EMPTY.getOrNull();

        boolean result = (isEmptyMusicDisc && hasMusicDiscItemType(otherStack) && !musicDiscGetted) || ItemStack.canCombine(stack, otherStack);

        if(isEmptyMusicDisc && result){
            musicDiscGetted = true;
        }

        return result  ;
//        return  (isEmptyMusicDisc && hasMusicDiscItemType(otherStack)) || ItemStack.canCombine(stack, otherStack)  ;
    }


    @Inject(method = "autofill(ILnet/minecraft/item/ItemStack;)V", at = @At("RETURN"))
    public void kenza_autofill(int slot, ItemStack stack, CallbackInfo ci) {
        musicDiscGetted = false;

    }

}
