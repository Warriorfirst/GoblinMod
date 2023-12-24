package goblinbanaan.features.miscellaneous;

import goblinbanaan.GoblinsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SellSuggester {

    public static final ResourceLocation SELL_SUGGESTER = new ResourceLocation(GoblinsMod.MODID, "textures/gui/sell_suggester.png");

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft mc = Minecraft.getMinecraft();
            EntityPlayerSP player = mc.thePlayer;

            // Loop through all slots in the player's inventory
            for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack itemStack = player.inventory.getStackInSlot(i);

                // Check if the ItemStack is not null and its display name contains "Astraea"
                if (itemStack != null && itemStack.getDisplayName().contains("Astraea")) {
                    //System.out.println("Astraea spotted in the inventory at slot " + i + ": " + itemStack.getDisplayName());

                    ScaledResolution scaledResolution = new ScaledResolution(mc);
                    int width = scaledResolution.getScaledWidth();
                    int height = scaledResolution.getScaledHeight();

                    // Constants or calculations for inventory GUI size
                    int inventoryWidth = 176;
                    int inventoryHeight = 166;

                    // Get the position of the player's inventory
                    GuiInventory inventoryGui = new GuiInventory(mc.thePlayer);
                    int guiLeft = (width - inventoryWidth) / 2;
                    int guiTop = (height - inventoryHeight) / 2;

                    int slotX = guiLeft + 8 + (i % 9) * 18;  // Calculate X position of the slot in the player's inventory
                    int slotY = guiTop + 18 + (i / 9) * 18; // Calculate Y position of the slot in the player's inventory

                    mc.getTextureManager().bindTexture(SELL_SUGGESTER);
                    // Draw a green background around the inventory slot
                    Gui.drawModalRectWithCustomSizedTexture(slotX, slotY, 0, 0, 16, 16, 16, 16);

                    // Optionally, you can break out of the loop if you only want to detect the first occurrence
                    break;
                }
            }
        }
    }
}
