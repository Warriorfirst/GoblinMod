package goblinbanaan.features.dungeons;

import goblinbanaan.utils.PlayerListUtil;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

import static goblinbanaan.GoblinsMod.mc;
import static goblinbanaan.utils.Utils.detectDungeonClass;

public class UltReminder {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();

        if (message.contains("testi")) {
            System.out.println("It contains testi");
            String classLevel = detectDungeonClass(mc.thePlayer.getName());
            System.out.println(classLevel);
            System.out.println("Player name = " + mc.thePlayer.getName());
//            List<String> playerList = PlayerListUtil.getTabList();
//
//            for (String playerName : playerList) {
//                System.out.println(playerName);
//            }
        }
    }
}
