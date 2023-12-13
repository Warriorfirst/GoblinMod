package goblinbanaan;

import gg.essential.api.EssentialAPI;
import goblinbanaan.config.GoblinConfig;
import goblinbanaan.commands.GoblinCommand;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import gg.essential.api.commands.Command;
import gg.essential.api.commands.DefaultHandler;
import gg.essential.api.commands.SubCommand;

import java.util.ArrayList;
import java.util.List;

@Mod(modid = GoblinMod.MODID, version = GoblinMod.VERSION)
public class GoblinMod {
    public static final String MODID = "GoblinsMod", VERSION = "1.0.0";

    @Mod.Instance(MODID)
    public static GoblinMod instance;

    private GoblinConfig goblinConfig;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        goblinConfig  = GoblinConfig.INSTANCE;



    }



    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        ClientCommandHandler commandHandler = ClientCommandHandler.instance;
        List<ICommand> commands = new ArrayList<>();
        commands.add(new GoblinCommand());

        for (ICommand command : commands) {
            if (!commandHandler.getCommands().containsValue(command)) {
                commandHandler.registerCommand(command);
            }
        }
    }

}