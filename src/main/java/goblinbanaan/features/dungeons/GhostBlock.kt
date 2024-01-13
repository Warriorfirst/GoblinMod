package goblinbanaan.features.dungeons

import goblinbanaan.GoblinsMod.keyBinds
import goblinbanaan.GoblinsMod.mc
import goblinbanaan.config.GoblinConfig
import goblinbanaan.event.BlockBreakEvent
import goblinbanaan.event.BlockChangeEvent
import goblinbanaan.event.ChunkUpdateEvent
import goblinbanaan.event.ClickEvent
import goblinbanaan.utils.PlayerUtil.leftClick
import goblinbanaan.utils.Utils.checkForSkyblock
import net.minecraft.block.state.IBlockState

import net.minecraft.init.Blocks
import net.minecraft.item.ItemPickaxe
import net.minecraft.util.BlockPos
import net.minecraft.util.MovingObjectPosition
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent


class GhostBlock {

    private fun isEnabled() = checkForSkyblock() && GoblinConfig.stonkDelay && !GoblinConfig.ghostBlock
    data class BlockData(var state: IBlockState, val time: Long, var queued: Boolean, var placed: Boolean)
    private val blocks = mutableMapOf<BlockPos, BlockData>()

    private val blacklist = listOf(
        Blocks.acacia_door,
        Blocks.anvil,
        Blocks.beacon,
        Blocks.bed,
        Blocks.birch_door,
        Blocks.brewing_stand,
        Blocks.brown_mushroom,
        Blocks.chest,
        Blocks.command_block,
        Blocks.crafting_table,
        Blocks.dark_oak_door,
        Blocks.daylight_detector,
        Blocks.daylight_detector_inverted,
        Blocks.dispenser,
        Blocks.dropper,
        Blocks.enchanting_table,
        Blocks.ender_chest,
        Blocks.furnace,
        Blocks.hopper,
        Blocks.jungle_door,
        Blocks.lever,
        Blocks.noteblock,
        Blocks.oak_door,
        Blocks.powered_comparator,
        Blocks.powered_repeater,
        Blocks.red_mushroom,
        Blocks.skull,
        Blocks.standing_sign,
        Blocks.stone_button,
        Blocks.trapdoor,
        Blocks.trapped_chest,
        Blocks.unpowered_comparator,
        Blocks.unpowered_repeater,
        Blocks.wall_sign,
        Blocks.wooden_button
    )

    // Deletes messages that are related to mining cooldown from chat
    @SubscribeEvent
    fun message(event: ClientChatReceivedEvent) {
        if ((event.message.unformattedText.startsWith("This ability is on cooldown for") ||
            event.message.unformattedText.startsWith("You cannot use this ability in this area!") ||
            event.message.unformattedText.startsWith("Mining Speed Boost is now available!" ) ||
            event.message.unformattedText.startsWith("You used your Mining Speed Boost Pickaxe Ability!")) &&
                GoblinConfig.ghostBlock && GoblinConfig.disableMiningCooldown && mc.thePlayer.heldItem?.item is ItemPickaxe) {
            event.isCanceled = true
        }
    }

    // Changes blocks to thin air
    private fun toAir(blockPos: BlockPos?): Boolean {
        if (blockPos != null) {
            val block = mc.theWorld.getBlockState(mc.objectMouseOver.blockPos).block
            if (!blacklist.contains(block)) {
                mc.theWorld.setBlockToAir(mc.objectMouseOver.blockPos)
                return true
            }
        }
        return false
    }

    // Ghost block when pressing/holding down the keybind
    @SubscribeEvent
    fun onTick(event: ClientTickEvent) {
        if (event.phase != TickEvent.Phase.START || !checkForSkyblock() || !keyBinds[0].isKeyDown || !GoblinConfig.gKey) return
        toAir(mc.objectMouseOver.blockPos)
    }

    /**
     * Modified from Skyblock Client under GNU Affero General Public License v3.0
     * https://github.com/Harry282/Skyblock-Client/blob/main/LICENSE
     * @author Harry282
     */

    // Right-clicking with a pickaxe will ghost block
    @SubscribeEvent
    fun onRightClick(event: ClickEvent.RightClickEvent) {
        if (!GoblinConfig.ghostBlock || !checkForSkyblock() || mc.objectMouseOver == null) return
        if (mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (mc.thePlayer.heldItem?.item is ItemPickaxe) {
                event.isCanceled = toAir(mc.objectMouseOver.blockPos)
                if(GoblinConfig.safeGhostBlock) {
                    leftClick()
                }
            }
        }
    }

    /**
     * Taken from Stonk Delay under GNU LESSER GENERAL PUBLIC LICENSE v3.0
     * https://github.com/appable0/StonkDelay/blob/main/LICENSE
     * @author Appable
     */

    @SubscribeEvent
    fun onBlockBreak(event: BlockBreakEvent) {
        if (!isEnabled()) return
        val state = mc.theWorld.getBlockState(event.pos)
        blocks[event.pos] = BlockData(
            state,
            System.currentTimeMillis(),
            queued = false,
            placed = false
        )
    }

    @SubscribeEvent
    fun onBlockChange(event: BlockChangeEvent) {
        if (!isEnabled()) return
        blocks[event.pos]?.let {
            it.state = event.state
            it.queued = true
            event.isCanceled = true
        }
    }

    @SubscribeEvent
    fun onChunkUpdate(event: ChunkUpdateEvent) {
        if (!isEnabled()) return
        val minX = event.packet.chunkX shl 4
        val minZ = event.packet.chunkZ shl 4
        val maxX = minX + 15
        val maxZ = minZ + 15

        blocks.forEach {
            if (it.key.x in minX..maxX && it.key.z in minZ..maxZ) {
                it.value.state = mc.theWorld.getBlockState(it.key)
                it.value.queued = true
                mc.theWorld.setBlockState(it.key, Blocks.air.defaultState)
            }
        }
    }

    // Resets expired queued blocks
    @SubscribeEvent
    fun onTick(event: TickEvent) {
        if (!isEnabled() || event.phase != TickEvent.Phase.START) return
        val currentTime = System.currentTimeMillis()
        blocks.keys.removeAll {
            val blockData = blocks[it]!!
            val timeExisted = currentTime - blockData.time
            val shouldResetBlock = blockData.queued
                    && timeExisted >= GoblinConfig.stonkDelayDuration
            if (shouldResetBlock) {
                mc.theWorld.setBlockState(it, blockData.state)
            }
            shouldResetBlock || (timeExisted >= 60000)
        }
    }

    // Stops tracking blocks when player right-clicks on adjacent block faces.
    @SubscribeEvent
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (!isEnabled() || event.action != PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) return
        val affectedPos = event.pos.offset(event.face) ?: return
        if (blocks[affectedPos]?.placed == false) {
            blocks.remove(affectedPos)
        }
    }

    // Resets block list when world changes.
    @SubscribeEvent
    fun onWorldUnload(ignored: WorldEvent.Unload) {
        blocks.clear()
    }
}