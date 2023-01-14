package com.kenza.discholder.profession

import com.kenza.discholder.registry.ModRegistries.MOD_MUSIC_DISC_EMPTY
import io.kenza.support.utils.Mathmc.between
import net.minecraft.entity.Entity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.item.*
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import net.minecraft.village.TradeOffers
import net.minecraft.village.TradeOffer
import java.util.*

class BuyMusicForEmeraldsFactory(maxUses: Int, experience: Int) : TradeOffers.Factory {

    private val maxUses: Int
    private val experience: Int
    private val multiplier: Float


    override fun create(entity: Entity?, random: Random?): TradeOffer {

        val itemStack = ItemStack(MOD_MUSIC_DISC_EMPTY.get(), 1)
        return TradeOffer(itemStack, ItemStack(Items.EMERALD).apply {
            count = between(4, 7)
        }, maxUses, experience, multiplier)
    }

    init {
        this.maxUses = maxUses
        this.experience = experience
//        multiplier = 0.05f
        multiplier = 0.05f
    }

}