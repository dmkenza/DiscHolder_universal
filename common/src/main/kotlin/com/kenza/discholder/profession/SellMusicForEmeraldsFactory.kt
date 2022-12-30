package com.kenza.discholder.profession

import com.kenza.discholder.utils.hasMusicDiscItemType
import io.kenza.support.utils.Mathmc.between
import net.minecraft.entity.Entity
import net.minecraft.entity.passive.VillagerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.MusicDiscItem
import net.minecraft.util.registry.Registry
import net.minecraft.village.TradeOffer
import net.minecraft.village.TradeOffers
import java.util.*

class SellMusicForEmeraldsFactory (maxUses: Int, experience: Int) : TradeOffers.Factory {

    private val maxUses: Int
    private val experience: Int
    private val multiplier: Float

    override fun create(entity: Entity?, random: net.minecraft.util.math.random.Random?): TradeOffer {

        val discs = Registry.ITEM.filter {
            it is MusicDiscItem
        }
        val offeredItems = (entity as VillagerEntity).offers.filter { it.sellItem.hasMusicDiscItemType() }.map { it.sellItem.item }

        val itemForBuy = (discs - discs.intersect(offeredItems)).run {
            get(between(0, this.size - 1))
        }

        return TradeOffer(ItemStack(Items.EMERALD).apply {
            count = between(15, 25)
        }, ItemStack(itemForBuy), maxUses, experience, multiplier)
    }

    init {
        this.maxUses = maxUses
        this.experience = experience
        multiplier = 0f
    }

}