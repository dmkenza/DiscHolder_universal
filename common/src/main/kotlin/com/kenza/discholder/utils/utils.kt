package com.kenza.discholder.utils

import com.kenza.discholder.MOD_ID
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.MusicDiscItem
import net.minecraft.util.Identifier

fun identifier(id: String) = Identifier(MOD_ID, id)

fun getSlotInBlock(inc: Double): Int {
    return if (inc < 1 / 16.0 || inc > 15 / 16.0) -1 else (8 * inc - .5).toInt()
}

fun ItemStack?.hasMusicDiscItemType(): Boolean {
    return (this?.item is MusicDiscItem)
}

fun Item?.hasMusicDiscItemType(): Boolean {
    return (this is MusicDiscItem)
}