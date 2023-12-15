package goblinbanaan.features.miscellaneous;

import goblinbanaan.config.GoblinConfig;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static goblinbanaan.utils.Utils.checkForSkyblock;

public class HealthCheck {

    protected boolean reminderCooldown = false;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String chatMessage = event.message.getUnformattedText();
        System.out.println(chatMessage);

        if (GoblinConfig.healReminder &&
                (chatMessage.startsWith("§6") || chatMessage.startsWith(" §c")) &&
                chatMessage.contains("§a❈ Defense     §b") &&
                chatMessage.endsWith("✎ Mana") && checkForSkyblock()) {

            int heartIndex = chatMessage.indexOf("❤");
            if (heartIndex == -1) return;

            String hpInfo = chatMessage.substring(0, heartIndex);

            int hpStartIndex = hpInfo.lastIndexOf('§');
            if (hpStartIndex == -1) return;

            hpInfo = hpInfo.substring(hpStartIndex + 1);
            String[] hpParts = hpInfo.split("/");
            String currentHP = hpParts[0].replaceAll("[^0-9,]", "");
            currentHP = currentHP.substring(1);
            String maxHP = hpParts[1].replaceAll("[^0-9,]", "");

            System.out.println("CurrentHP: " + currentHP);
            System.out.println("MaxHP: " + maxHP);
        }
    }
}
