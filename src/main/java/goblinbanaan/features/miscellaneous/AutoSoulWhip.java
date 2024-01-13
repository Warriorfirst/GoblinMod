package goblinbanaan.features.miscellaneous;

import goblinbanaan.config.GoblinConfig;
import goblinbanaan.utils.ItemUtils;
import goblinbanaan.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.awt.event.InputEvent;


public class AutoSoulWhip {

    private boolean cancelWhip = true;

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!(Utils.checkForDungeons()) && !(GoblinConfig.autoSoulWhip)) return;
        if (GoblinConfig.autoSoulWhip) return; // Delete this after it's fixed

        if (event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_AIR) ||
                event.action.equals(PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK)) {
            if (isHoldingSoulWhip() && checkMouseInput() && cancelWhip) {
                event.setCanceled(true);

                // BUG: Does not work while holding down right click
                try {
                    event.setCanceled(false);
                    Robot robot = new Robot();
                    robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                    System.out.println("Clicking");
                    robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                    robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                    System.out.println("2");
                    robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                } catch (AWTException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static boolean checkMouseInput() {
        if (Mouse.isButtonDown(1)){
             return true;
        } else {
            return false;
        }
    }

    public static boolean isHoldingSoulWhip() {
        ItemStack heldItem = Utils.GetMC().thePlayer.getHeldItem();

        if (heldItem != null) {
            String itemName = ItemUtils.getSkyBlockItemID(heldItem);
            if (itemName != null && itemName.equals("SOUL_WHIP")) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }
}
