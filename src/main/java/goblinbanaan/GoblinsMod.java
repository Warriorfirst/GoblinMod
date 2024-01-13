package goblinbanaan;

import goblinbanaan.config.GoblinConfig;
import goblinbanaan.commands.GoblinCommand;
import goblinbanaan.features.dungeons.*;
import goblinbanaan.features.miscellaneous.AutoHarp;
import goblinbanaan.features.miscellaneous.AutoLeap;
import goblinbanaan.features.miscellaneous.AutoSoulWhip;
import goblinbanaan.features.miscellaneous.HealthCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.ICommand;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod(modid = GoblinsMod.MODID, name = GoblinsMod.NAME, version = GoblinsMod.VERSION, clientSideOnly = true)
public class GoblinsMod {
    public static final String MODID = "goblinsmod", VERSION = "1.0.1", NAME = "GoblinsMod";

    @Mod.Instance(MODID)
    public static GoblinsMod instance;

    private GoblinConfig goblinConfig;
    public static final Minecraft mc = Minecraft.getMinecraft();

    public static final List<KeyBinding> keyBinds = Arrays.asList(
            new KeyBinding("Ghost Block", Keyboard.KEY_G, "Goblins Mod")
    );


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        goblinConfig  = GoblinConfig.INSTANCE;
    }



    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

        // Features to load
        List<Object> features = Arrays.asList(
                this,
                new DeathMessage(),
                new PartyCommands(),
                new HealthCheck(),
                new FireFreezeTimer(),
                //new SellSuggester(),
                //new UltReminder(),
                new QuickCloseChest(),
                new AutoHarp(),
                new AutoSoulWhip(),
                new GhostBlock(),
                new AutoLeap(),
                new BonzoTimer(),
                new PhoenixTimer()

        );
        features.forEach(MinecraftForge.EVENT_BUS::register);

        keyBinds.forEach(ClientRegistry::registerKeyBinding);
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
