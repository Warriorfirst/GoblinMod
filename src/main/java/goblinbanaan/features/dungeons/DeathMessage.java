package goblinbanaan.features.dungeons;

import goblinbanaan.config.GoblinConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import static goblinbanaan.utils.Utils.inDungeons;
import java.util.Random;


public class DeathMessage {

    private static final Random random = new Random();

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();




        // Add so it does forgor
        // This if-statements checks if dungeonDeathMessage is toggled to true, and it then checks
        // whether someone has died or not and will give certain output.(This also ignores if you've died.)
        if (GoblinConfig.dungeonDeathMessage && inDungeons && message.startsWith("☠") && (message.contains("killed by") || message.contains("died to a trap")) && message.contains("and became a ghost.") && !(message.contains("☠ You ")) && !(message.contains("☠ You died to a trap and became a ghost."))) {
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            if (!GoblinConfig.dungeonDeathMessageText.isEmpty()) {
                player.sendChatMessage(GoblinConfig.dungeonDeathMessageText);
            } else {
                int randomNumber = random.nextInt(7) + 1;

                switch (randomNumber) {
                    case 1:
                        player.sendChatMessage("Nice death");
                        break;
                    case 2:
                        player.sendChatMessage("Skill issue");
                        break;
                    case 3:
                        player.sendChatMessage("Womp Womp");
                        break;
                    case 4:
                        player.sendChatMessage("Try to survive challenge (impossible)");
                        break;
                    case 5:
                        player.sendChatMessage("Nice pb");
                        break;
                    case 6:
                        player.sendChatMessage("What color is your drag skip?");
                        break;
                    case 7:
                        player.sendChatMessage("Good night!");
                        break;
                    default:
                        player.sendChatMessage("rip");
                        break;
                }
            }
        }
    }
}