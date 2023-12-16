package goblinbanaan.features.miscellaneous;

import goblinbanaan.config.GoblinConfig;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static goblinbanaan.utils.Utils.checkForSkyblock;
import static goblinbanaan.utils.Utils.drawTitle;

public class HealthCheck {

    protected boolean reminderCooldown = false;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String chatMessage = event.message.getUnformattedText();

        if (GoblinConfig.healReminder &&
                (chatMessage.startsWith("§6") || chatMessage.startsWith("§c")) &&
                chatMessage.contains("✎ Mana") && checkForSkyblock()) {

            int heartIndex = chatMessage.indexOf("❤");
            if (heartIndex == -1) return;

            String hpInfo = chatMessage.substring(0, heartIndex);
            int hpStartIndex = hpInfo.lastIndexOf('§');
            if (hpStartIndex == -1) return;

            hpInfo = hpInfo.substring(hpStartIndex + 1);
            String[] hpParts = hpInfo.split("/");

            int currentHP = Integer.parseInt(hpParts[0].replaceAll("[^0-9]", ""));
            int maxHP = Integer.parseInt(hpParts[1].replaceAll("[^0-9]", ""));


            if (GoblinConfig.healReminder && GoblinConfig.healthPercentage != 0) {
                if (currentHP <= (maxHP * GoblinConfig.healthPercentage)) {
                    drawTitle("Heal up!", null, 30);
                    Minecraft.getMinecraft().ingameGUI.displayTitle("§cHeal up!", null,  0, 30, 0);
                }
            }
        }
    }
}