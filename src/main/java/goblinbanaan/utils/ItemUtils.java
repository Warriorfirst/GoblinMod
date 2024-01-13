/**
 * Modified from SkyblockFeatures under GNU Lesser General Public License v3.0
 * https://github.com/MrFast-js/SkyblockFeatures/blob/master/LICENSE
 * @author MrFast-js
 */

package goblinbanaan.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class ItemUtils {

    public static final int NBT_STRING = 8;

    /**
     * Returns the display name of a given item
     *
     * @param item the Item to get the display name of
     * @return the display name of the item
     * @author Mojang
     */
    public static String getDisplayName(ItemStack item) {
        String s = item.getItem().getItemStackDisplayName(item);

        if (item.getTagCompound() != null && item.getTagCompound().hasKey("display", 10)) {
            NBTTagCompound nbttagcompound = item.getTagCompound().getCompoundTag("display");

            if (nbttagcompound.hasKey("Name", 8)) {
                s = nbttagcompound.getString("Name");
            }
        }

        return s;
    }

    /**
     * Returns the Skyblock Item ID of a given Skyblock item
     *
     * @param item the Skyblock item to check
     * @return the Skyblock Item ID of this item or {@code null} if this isn't a valid Skyblock item
     * @author BiscuitDevelopment
     */
    public static String getSkyBlockItemID(ItemStack item) {
        if (item == null) {
            return null;
        }

        NBTTagCompound extraAttributes = getExtraAttributes(item);
        if (extraAttributes == null) {
            return null;
        }

        if (!extraAttributes.hasKey("id", ItemUtils.NBT_STRING)) {
            return null;
        }

        return extraAttributes.getString("id");
    }

    public static String getItemUUID(ItemStack item) {
        if (item == null) {
            return null;
        }

        NBTTagCompound extraAttributes = getExtraAttributes(item);
        if (extraAttributes == null) {
            return null;
        }

        if (!extraAttributes.hasKey("uuid", ItemUtils.NBT_STRING)) {
            return null;
        }

        return extraAttributes.getString("uuid");
    }

    /**
     * Returns the {@code ExtraAttributes} compound tag from the item's NBT data.
     *
     * @param item the item to get the tag from
     * @return the item's {@code ExtraAttributes} compound tag or {@code null} if the item doesn't have one
     * @author BiscuitDevelopment
     */
    public static NBTTagCompound getExtraAttributes(ItemStack item) {
        if (item == null || !item.hasTagCompound()) {
            return null;
        }

        return item.getSubCompound("ExtraAttributes", false);
    }

    /**
     * Returns the Skyblock Item ID of a given Skyblock Extra Attributes NBT Compound
     *
     * @param extraAttributes the NBT to check
     * @return the Skyblock Item ID of this item or {@code null} if this isn't a valid Skyblock NBT
     * @author BiscuitDevelopment
     */
    public static String getSkyBlockItemID(NBTTagCompound extraAttributes) {
        if (extraAttributes != null) {
            String itemId = extraAttributes.getString("id");

            if (!itemId.isEmpty()) {
                return itemId;
            }
        }

        return null;
    }
}