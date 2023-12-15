package goblinbanaan.features.miscellaneous;

import goblinbanaan.config.GoblinConfig;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HealthCheck {

    protected boolean reminderCooldown = false;

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String checkForHp = event.message.getUnformattedText();
        // Goud hp
        // §64,209/3,909❤     §a864§a❈ Defense     §b2,284/2,284✎ Mana

        // red
        // §c3,909/3,909❤     §a867§a❈ Defense     §b1,031/2,292✎ Mana
        System.out.println(checkForHp);

        if(GoblinConfig.healReminder && (checkForHp.startsWith(" §6") || checkForHp.startsWith(" §c")) && checkForHp.contains("§a❈ Defense     §b") && checkForHp.endsWith("✎ Mana")) {
            String modifiedString = checkForHp.substring(3);
            System.out.println("Modified String: " + modifiedString);
            int heartIndex = modifiedString.indexOf("❤");

            if (heartIndex != -1) {
            String rightSideOfHpRemoved = modifiedString.substring(0, heartIndex);
            }
        }
    }
}
