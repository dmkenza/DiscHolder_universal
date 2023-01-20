package com.kenza.discholder.registry

import com.google.common.base.Suppliers
import com.google.common.collect.ImmutableSet
import com.kenza.discholder.MOD_ID
import com.kenza.discholder.block.DiscHolderBlock
import com.kenza.discholder.commonPlatformHelper
import com.kenza.discholder.entity.DiscHolderBlockEntity
import com.kenza.discholder.registry.ModProfession.registerBlockStates
import com.kenza.discholder.registry.ModProfession.registerTrades
import com.kenza.discholder.render.DiscHolderBlockEntityGuiDescription
import com.kenza.discholder.utils.identifier
import dev.architectury.registry.menu.MenuRegistry
import dev.architectury.registry.registries.DeferredRegister
import dev.architectury.registry.registries.Registries
import dev.architectury.registry.registries.RegistrySupplier
import io.github.cottonmc.cotton.gui.impl.LibGuiCommon
import io.kenza.support.utils.*
import io.kenza.support.utils.Ref.BLOCKS
import io.kenza.support.utils.Ref.BLOCK_ENTITY_TYPES
import io.kenza.support.utils.Ref.ITEMS
import io.kenza.support.utils.Ref.POINT_OF_INTEREST_TYPES
import io.kenza.support.utils.Ref.SCREEN_HANDLERS
import io.kenza.support.utils.Ref.SOUNDS_EVENTS
import io.kenza.support.utils.Ref.VILLAGER_PROFESSIONS
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.item.*
import net.minecraft.network.PacketByteBuf
import net.minecraft.screen.ScreenHandlerContext
import net.minecraft.screen.ScreenHandlerType
import net.minecraft.sound.SoundEvent
import net.minecraft.util.DyeColor
import net.minecraft.util.Rarity
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.village.VillagerProfession
import net.minecraft.world.poi.PointOfInterestType
import java.util.function.Supplier

object ModRegistries {

    init {
        ITEMS = DeferredRegister.create<Item>(MOD_ID, Registry.ITEM_KEY)
        BLOCKS = DeferredRegister.create(MOD_ID, Registry.BLOCK_KEY)
        BLOCK_ENTITY_TYPES = DeferredRegister.create(MOD_ID, Registry.BLOCK_ENTITY_TYPE_KEY)

        POINT_OF_INTEREST_TYPES = DeferredRegister.create(MOD_ID, Registry.POINT_OF_INTEREST_TYPE_KEY)
        VILLAGER_PROFESSIONS = DeferredRegister.create(MOD_ID, Registry.VILLAGER_PROFESSION_KEY)

        SOUNDS_EVENTS =
            DeferredRegister.create<SoundEvent>(MOD_ID, Registry.SOUND_EVENT_KEY)
        SCREEN_HANDLERS = DeferredRegister.create<ScreenHandlerType<*>>(MOD_ID, Registry.MENU_KEY)

    }

    val musicsMap = listOf(
        "record.baroque_nightmare" to "music_disc_baroque_nightmare",
        "record.cleopona" to "music_disc_cleopona",
        "record.for_the_new_lunar_republic" to "music_disc_for_the_new_lunar_republic",
        "record.no_strings_attached" to "music_disc_no_strings_attached",
        "record.sun_burst" to "music_disc_sun_burst",
        "record.harmony" to "music_disc_harmony",
        "record.spirit_animal" to "music_disc_spirit_animal",
    )

    lateinit var MOD_TAB: ItemGroup
    lateinit var DISC_BLOCKENTITY_GUI_HANDLER_TYPE: ScreenHandlerType<DiscHolderBlockEntityGuiDescription>

    val MOD_ENTITY_TYPES_MAP = HashMap<String, RegistrySupplier<BlockEntityType<*>>>()
    val MOD_BLOCKS_MAP = HashMap<String, RegistrySupplier<Block>>()

    lateinit var MOD_SOUND_DJ_POI: Supplier<SoundEvent>
    lateinit var MOD_DJ_POI: Supplier<PointOfInterestType>
    lateinit var MOD_MUSIC_DISC_EMPTY: RegistrySupplier<Item>
    lateinit var MOD_DJ_PROFESSION: Supplier<VillagerProfession>


    val UPDATE_INV_PACKET_ID = identifier("update_inv_packet_id")

    val REGISTRIES: Supplier<Registries> = Suppliers.memoize {
        Registries.get(MOD_ID)
    }


    fun onInit() {



        registerDischodlers()

        identifier("music_disc_empty").item(EMPTY_ITEM).let {
            MOD_MUSIC_DISC_EMPTY = it
        }

        MOD_SOUND_DJ_POI = REGISTRIES.get().get(Registry.SOUND_EVENT_KEY)
            .register(identifier(MOD_ID), { SoundEvent(identifier("profession.dj")) })


//        MOD_SOUND_DJ_POI = identifier("profession.dj").soundEvent()


        musicsMap.forEach {
            val (soundId, itemId) = it
            identifier(soundId).apply {
                soundEvent().let {
                    identifier(itemId).item {
                        createMusicDisc(it)
                    }
                }
            }
        }
        identifier("profession.dj").apply {
            SoundEvent(this)
        }

        DISC_BLOCKENTITY_GUI_HANDLER_TYPE =
            MenuRegistry.ofExtended { syncId: Int, inv: PlayerInventory, buf: PacketByteBuf? ->
//                val buf =  PacketByteBuf(Unpooled.buffer())
                DiscHolderBlockEntityGuiDescription(
                    syncId, inv, ScreenHandlerContext.create(inv.player.world, buf?.readBlockPos())
                )
            }

        identifier("disc_holder_menu").screenHandler {
            DISC_BLOCKENTITY_GUI_HANDLER_TYPE
        }


//        if (Platform.isForge()) {
        identifier("dj").apply {
            poiType {
                val block = Blocks.JUKEBOX as Block
//                block
                PointOfInterestType(
                    ImmutableSet.copyOf<BlockState>(block.stateManager.states), 1, 1
                )
            }.let {
                MOD_DJ_POI = it
            }
        }

        identifier("dj").apply {
            profession {
                VillagerProfession(
                    "dj",
                    { holder: RegistryEntry<PointOfInterestType> -> holder.value() as PointOfInterestType == MOD_DJ_POI.get() },
                    { holder: RegistryEntry<PointOfInterestType> -> holder.value() as PointOfInterestType == MOD_DJ_POI.get() },
                    ImmutableSet.of(),
                    ImmutableSet.of(),
                    MOD_SOUND_DJ_POI.get()
                )
            }.let {
                MOD_DJ_PROFESSION = it
            }
        }

        SOUNDS_EVENTS.register()
        BLOCKS.register()
        ITEMS.register()
        BLOCK_ENTITY_TYPES.register()
        SCREEN_HANDLERS.register()

        POINT_OF_INTEREST_TYPES.register()
        VILLAGER_PROFESSIONS.register()

//        MOD_ENTITY_TYPES_MAP.map { type ->
//            val f1 = BlockEntityType.getId(type.value.get())
//            println("$f1")
//        }


//        DyeColor.values().map { dyeColor ->
//            val itemId = dyeColor.name.lowercase() + "_discholder"
//            val type = MOD_ENTITY_TYPES_MAP.get(itemId) as RegistrySupplier<BlockEntityType<DiscHolderBlockEntity>>
//            Registry.register(Registry.BLOCK_ENTITY_TYPE, identifier(itemId), type.get())
//        }


//        WGridPanel().insets = Insets.ROOT_PANEL
//        val x = WGridPanel().backgroundPainter
//        DiscHolderBlockEntityGuiDescription2(2,null, null)

    }

    private fun registerProfessionData() {
        registerTrades()
        registerBlockStates()
    }


    private fun registerDischodlers() {

        val tabItemName = "blue_discholder"

        DyeColor.values().map { dyeColor ->
            val itemId = dyeColor.name.lowercase() + "_discholder"
            registerDiscHolder(itemId)
        }

        MOD_TAB = commonPlatformHelper.registerCreativeModeTab(identifier("discholder_tab")) {
            ItemStack(MOD_BLOCKS_MAP[tabItemName]?.get())
        }
    }

    private fun registerDiscHolder(itemId: String) {


        var type: Supplier<BlockEntityType<DiscHolderBlockEntity>>? = null

        Supplier {
//            FabricBlockEntityTypeBuilder.create(
            BlockEntityType.Builder.create(
                { pos: BlockPos, state: BlockState ->
                    DiscHolderBlockEntity(
                        itemId,
                        type!!.get(),
                        pos,
                        state
                    )
                }, MOD_BLOCKS_MAP[itemId]?.get()
            )
                .build(null)
        }
            .apply {
                type = this
            }

        val block = {
            DiscHolderBlock(
                itemId,
                AbstractBlock.Settings.of(Material.WOOD).requiresTool().strength(6f),
                ::DiscHolderBlockEntityGuiDescription
            )
        }

        identifier(itemId).apply {
            block { block() }.let {
                MOD_BLOCKS_MAP.put(itemId, it)
            }
//            val type = commonPlatformHelper.registerBlockEntityType(
//                itemId,
//            )
//            GlobalScope.launch {
//                delay(1000)
//                val x = type.get()
//            }

            item { BlockItem(MOD_BLOCKS_MAP[itemId]?.get(), Item.Settings().group(MOD_TAB)) }
            blockEntityType {
                type!!.get()
            }.let {
                MOD_ENTITY_TYPES_MAP.put(itemId, it)
            }
        }

    }

    private fun createMusicDisc(sound: RegistrySupplier<SoundEvent>) =
        MusicDiscItem(
            1, sound.get(), Item.Settings()
                .maxCount(1)
                .group(MOD_TAB)
                .rarity(Rarity.RARE),
            0
        )


    fun onSetupEvent(event: Any) {
        registerProfessionData()
    }


}