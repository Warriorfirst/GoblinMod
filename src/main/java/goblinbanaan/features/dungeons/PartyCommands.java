package goblinbanaan.features.dungeons;

import gg.essential.lib.mixinextras.lib.apache.commons.ArrayUtils;
import goblinbanaan.config.GoblinConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PartyCommands {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String checkForFloor = event.message.getUnformattedText();

        // Example text: Party > [VIP+] Goblinbanaan: !m7
        if (GoblinConfig.queueCommands && checkForFloor.startsWith("§9Party §8> ") &&
                (checkForFloor.contains(": !m") || checkForFloor.contains(": !f"))) {
            String[] words = checkForFloor.split(" ");


            if (words.length > 1 && words[1].startsWith("[") && words[1].endsWith("]")) {
                words = ArrayUtils.remove(words, 1);
            }

            String modifiedMessage = String.join(" ", words);
            String[] modifiedWords = modifiedMessage.split(" ");

            if (modifiedWords.length > 2 && modifiedWords[3].endsWith(":")) {
                String command = modifiedWords[4];

                if (command.length() >= 2) {
                    String lastTwoLetters = command.substring(command.length() - 2);
                    lastTwoLetters = lastTwoLetters.toLowerCase();
                    EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;

                    switch (lastTwoLetters) {
                        case "f1":
                            player.sendChatMessage("/joininstance CATACOMBS_FLOOR_ONE");
                            break;
                        case "f2":
                            player.sendChatMessage("/joininstance CATACOMBS_FLOOR_TWO");
                            break;
                        case "f3":
                            player.sendChatMessage("/joininstance CATACOMBS_FLOOR_THREE");
                            break;
                        case "f4":
                            player.sendChatMessage("/joininstance CATACOMBS_FLOOR_FOUR");
                            break;
                        case "f5":
                            player.sendChatMessage("/joininstance CATACOMBS_FLOOR_FIVE");
                            break;
                        case "f6":
                            player.sendChatMessage("/joininstance CATACOMBS_FLOOR_SIX");
                            break;
                        case "f7":
                            player.sendChatMessage("/joininstance CATACOMBS_FLOOR_SEVEN");
                            break;
                        case "m1":
                            player.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_TWO");
                            break;
                        case "m2":
                            player.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_ONE");
                            break;
                        case "m3":
                            player.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_THREE");
                            break;
                        case "m4":
                            player.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_FOUR");
                            break;
                        case "m5":
                            player.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_FIVE");
                            break;
                        case "m6":
                            player.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_SIX");
                            break;
                        case "m7":
                            player.sendChatMessage("/joininstance MASTER_CATACOMBS_FLOOR_SEVEN");
                            break;
                        default:
                            player.addChatMessage(new ChatComponentText("§b[§3GoblinsMod§b] §cThe given instance to join must be between F1-7 and M1-7.\nFor more information DM BabyGetSwekt#0."));
                            break;
                    }
                }
            }
        }
    }
}

