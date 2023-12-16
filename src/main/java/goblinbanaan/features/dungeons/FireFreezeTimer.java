package goblinbanaan.features.dungeons;

import goblinbanaan.config.GoblinConfig;
import goblinbanaan.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static goblinbanaan.utils.Utils.drawTitle;

public class FireFreezeTimer {
    String professorLine = "[BOSS] The Professor: Oh? You found my Guardians' one weakness?";


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String fireFreezeMessage = event.message.getUnformattedText();

        // fireFreezeMessage.contains(professorLine) &&
        if (GoblinConfig.fireFreezeTimer && fireFreezeMessage.contains(professorLine)) {
            drawTitle("", null, 30);
            Utils.setTimeout(()->{
                Minecraft.getMinecraft().ingameGUI.displayTitle("§cFire freeze in 5 seconds!", null,  0, 20, 0);
                Utils.setTimeout(()->{
                    Minecraft.getMinecraft().ingameGUI.displayTitle("§cFire freeze in 4 seconds!", null,  0, 20, 0);
                    Utils.setTimeout(()->{
                        Minecraft.getMinecraft().ingameGUI.displayTitle("§cFire freeze in 3 seconds!", null,  0, 20, 0);
                        Utils.setTimeout(()->{
                            Minecraft.getMinecraft().ingameGUI.displayTitle("§cFire freeze in 2 seconds!", null,  0, 20, 0);
                            Utils.setTimeout(()->{
                                Minecraft.getMinecraft().ingameGUI.displayTitle("§cFire freeze in 1 seconds!", null,  0, 20, 0);
                                Utils.setTimeout(()->{
                                    Minecraft.getMinecraft().ingameGUI.displayTitle("§cFire freeze NOW!", null,  0, 20, 0);
                                },875);
                            },875);
                        },875);
                    },875);
                },875);
            },875);
        }
    }
}
