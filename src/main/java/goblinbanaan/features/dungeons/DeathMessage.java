package goblinbanaan.features.dungeons;

import goblinbanaan.config.GoblinConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static goblinbanaan.config.GoblinConfig.dungeonDeathMessage;

public class DeathMessage {

    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        if (message.contains("Hoi ik ben kevin")) {
            EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
            Minecraft.getMinecraft().thePlayer.sendChatMessage("Gn shitter");
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Aint no way u just died"));
            System.out.println("Dit is de dungeon death message: " + dungeonDeathMessage);

        }
    }
}