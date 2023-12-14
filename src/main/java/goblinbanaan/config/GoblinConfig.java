package goblinbanaan.config;

import gg.essential.api.utils.GuiUtil;
import gg.essential.vigilance.Vigilance;
import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;
import goblinbanaan.GoblinMod;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeVersion;

import java.io.File;

public class GoblinConfig extends Vigilant {

    @Property(
            type = PropertyType.SWITCH, name = "Dungeon Death Message",
            description = "Displays a funny death message when someone in your party dies.",
            category = "Dungeon", subcategory = "Funny stuff",
            searchTags = {"Message", "Dead"}
    )
    public static boolean dungeonDeathMessage = false;

    public static GoblinConfig INSTANCE = new GoblinConfig();

    public GoblinConfig() {
        super(new File("./config/goblinmod.toml"), "GoblinsMod (" + GoblinMod.VERSION + ")");
        initialize();
    }

}
