package goblinbanaan.features.dungeons;

import goblinbanaan.utils.PlayerListUtil;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class UltReminder {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

        if (message.contains("testi")) {
            System.out.println("It contains testi");
            List<String> playerList = PlayerListUtil.getTabList();

            for (String playerName : playerList) {
                System.out.println(playerName);
            }
        }
    }
}
