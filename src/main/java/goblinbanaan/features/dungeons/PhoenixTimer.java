package goblinbanaan.features.dungeons;

import goblinbanaan.config.GoblinConfig;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static goblinbanaan.utils.Utils.*;


public class PhoenixTimer{

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformatted();
        
        if(GoblinConfig.phoenixTimer && message.contains("Your Phoenix Pet saved you from certain death!")) {
            drawTitle("", null, 20);
            Utils.setTimeout(() -> {
                Minecraft.getMinecraft().ingameGUI.displayTitle("Leap now!", null, 0, 20, 0);
            }, 3750);
        }

    }
}