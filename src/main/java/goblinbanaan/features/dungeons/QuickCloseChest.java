/**
 * Modified from SkyblockFeatures under GNU Lesser General Public License v3.0
 * https://github.com/MrFast-js/SkyblockFeatures/blob/master/LICENSE
 * @author MrFast-js
 */

package goblinbanaan.features.dungeons;

import goblinbanaan.config.GoblinConfig;
import goblinbanaan.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.inventory.ContainerChest;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class QuickCloseChest {

    @SubscribeEvent
    public void onKeyInput(GuiScreenEvent.KeyboardInputEvent keyboardInputEvent) {
        GuiScreen screen = Minecraft.getMinecraft().currentScreen;
        if(!Utils.checkForDungeons() && GoblinConfig.quickCloseChest) return;

        if (screen instanceof GuiChest) {
            ContainerChest containerChest = (ContainerChest) ((GuiChest) screen).inventorySlots;
            if (!(("Large Chest").equals(containerChest.getLowerChestInventory().getName()) || "Chest".equals(containerChest.getLowerChestInventory().getName()))) return;
            Minecraft.getMinecraft().thePlayer.closeScreen();
        }
    }
}
