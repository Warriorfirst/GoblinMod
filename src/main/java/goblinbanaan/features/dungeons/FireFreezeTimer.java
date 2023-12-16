package goblinbanaan.features.dungeons;

import goblinbanaan.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.mojang.realmsclient.gui.ChatFormatting;

import java.util.Timer;
import java.util.TimerTask;

import static goblinbanaan.utils.Utils.drawTitle;

public class FireFreezeTimer {


    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String fireFreezeMessage = event.message.getUnformattedText();
        String professorLine = "[BOSS] The Professor: Oh? You found my Guardians' one weakness?";

        if (fireFreezeMessage.contains(professorLine)) {
            drawTitle("1", null, 18);
            Minecraft.getMinecraft().ingameGUI.displayTitle("§bFire Freeze in: §35.00s", null, 0, 30, 0);

        }
    }
}

