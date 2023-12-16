package goblinbanaan.features.dungeons;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Timer;
import java.util.TimerTask;

import static goblinbanaan.utils.Utils.drawTitle;

public class FireFreezeTimer {

    private long ffCountdownTo = 0;
    private int test = 1; // Make test a class-level variable

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String fireFreezeMessage = event.message.getUnformattedText();

        if (fireFreezeMessage.startsWith("§8[§3279§8] §d[LOWBALLER] §a[VIP§6+§a] Goblinbanaan§f")) {
            double fireFreezeTimer = 5.00;

            System.out.println("IM DRAWING A TITLE WOOOOOOOOO"); //debug
            if (test == 1) {
                drawTitle("1", null, 18);
                scheduleNextTitle("§bFire Freeze in: §35.00s", 30);
                drawTitle("2", null, 18);
                scheduleNextTitle("§bFire Freeze in: §33.00s", 30);
            }
            System.out.println("I DREW A TITLE WOOOOOOOO"); // debug
        }
    }

    private void scheduleNextTitle(final String title, final int durationTicks) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Minecraft.getMinecraft().ingameGUI.displayTitle(title, null, 0, durationTicks, 0);
            }
        }, 1000); // Delay for 1 second (adjust as needed)
    }
}
