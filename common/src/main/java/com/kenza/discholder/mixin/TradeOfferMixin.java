package com.kenza.discholder.mixin;

import net.minecraft.item.ItemStack;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.kenza.discholder.registry.ModRegistries.MOD_MUSIC_DISC_EMPTY;
import static com.kenza.discholder.utils.UtilsKt.hasMusicDiscItemType;

@Mixin(TradeOffer.class)
class TradeOfferMixin {


    @Inject(method = "acceptsBuy(Lnet/minecraft/item/ItemStack;Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    public void test(ItemStack given, ItemStack sample, CallbackInfoReturnable<Boolean> cir) {

        if (sample.isEmpty() && given.isEmpty()) {

        } else {

            boolean isEmptyMusicDisc = sample.getItem() == MOD_MUSIC_DISC_EMPTY.getOrNull();

            if (isEmptyMusicDisc && hasMusicDiscItemType(given)) {
                cir.setReturnValue(true);
            }
        }

    }

}
