package goblinbanaan.commands;

import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
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

import java.util.List;

public class RepartyCommand extends CommandBase {

    @Override
    public String getCommandName() {
        return "reparty";
    }

    @Override
    public List<String> getCommandAliases() {
        return Lists.newArrayList("goblinrp","gmrp");
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return "/" + getCommandName();
    }


    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender arg0, String[] arg1) throws CommandException {
        Minecraft.getMinecraft().thePlayer.sendChatMessage("Reached milestones 3! WEEEEWOOOOOOOWEEEWOOOOOOO");
    }
}
