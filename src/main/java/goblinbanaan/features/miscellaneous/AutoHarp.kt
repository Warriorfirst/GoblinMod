/**
 * Taken from Floppa Client under AGPL 3.0 license
 * https://github.com/FloppaCoding/FloppaClient/blob/master/LICENSE
 * @author Aton
 */

package goblinbanaan.features.miscellaneous

import goblinbanaan.config.GoblinConfig
import goblinbanaan.utils.Utils
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.init.Blocks
import net.minecraft.inventory.ContainerChest
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.GuiOpenEvent
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class AutoHarp {
    private var inHarp = false
    private var lastInv = 0


    @SubscribeEvent
    fun onGuiOpen(event: GuiOpenEvent) {
        if (event.gui !is GuiChest) return
        if (!Utils.checkForSkyblock() || !GoblinConfig.autoMelody) return
        val container = (event.gui as GuiChest).inventorySlots
        if (container is ContainerChest) {
            val chestName = container.lowerChestInventory.displayName.unformattedText
            if (chestName.startsWith("Harp -")) {
                inHarp = true
            }
        }
    }

    @SubscribeEvent
    fun onRender(event: GuiScreenEvent.BackgroundDrawnEvent) {
        if (!inHarp || Minecraft.getMinecraft().thePlayer == null) return
        val container = Minecraft.getMinecraft().thePlayer.openContainer ?: return
        if (container !is ContainerChest) return
        val inventoryName = container.inventorySlots?.get(0)?.inventory?.name
        if (inventoryName == null || !inventoryName.startsWith("Harp -")) {
            inHarp = false
            return
        }
        val newHash = container.inventorySlots.subList(0,36).joinToString("") { it?.stack?.displayName ?: "" }.hashCode()
        if (lastInv == newHash) return
        lastInv = newHash
        for (ii in 0..6) {
            val slot = container.inventorySlots[37 + ii]
            if ((slot.stack?.item as? ItemBlock)?.block === Blocks.quartz_block) {
                Minecraft.getMinecraft().playerController.windowClick(
                    container.windowId,
                    slot.slotNumber,
                    2,
                    3,
                    Minecraft.getMinecraft().thePlayer
                )
                break
            }
        }
    }
}