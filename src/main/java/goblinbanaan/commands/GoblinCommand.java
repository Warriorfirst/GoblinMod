package goblinbanaan.commands;

import goblinbanaan.utils.GuiUtils;
import gg.essential.api.EssentialAPI;
import gg.essential.universal.UChat;
import goblinbanaan.GoblinMod;
import goblinbanaan.config.GoblinConfig;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.commands.DisplayName;
import gg.essential.api.commands.Greedy;
import gg.essential.api.commands.Options;
import gg.essential.api.commands.SubCommand;
import gg.essential.api.utils.GuiUtil;
import goblinbanaan.utils.Utils;
import net.minecraft.client.Minecraft;
import gg.essential.vigilance.gui.SettingsGui;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GoblinCommand extends CommandBase {

    private final Minecraft mc = Minecraft.getMinecraft();

    @Override
    public String getCommandName() {
        return "goblinmod";
    }


    @Override
    public List<String> getCommandAliases() {
        return Lists.newArrayList("gm","goblin");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + getCommandName();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return (args.length >= 1) ? getListOfStringsMatchingLastWord(args, Utils.getListOfPlayerUsernames()) : null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        EntityPlayerSP player = (EntityPlayerSP) sender;
        if (args.length == 0) {
            GuiUtil.open(Objects.requireNonNull(GoblinConfig.INSTANCE.gui()));
        }


    }


    }


