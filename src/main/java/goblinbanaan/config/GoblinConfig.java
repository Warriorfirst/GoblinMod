package goblinbanaan.config;

import gg.essential.vigilance.Vigilant;
import gg.essential.vigilance.data.Property;
import gg.essential.vigilance.data.PropertyType;
import goblinbanaan.GoblinsMod;

import java.io.File;

public class GoblinConfig extends Vigilant {

    @Property(
            type = PropertyType.SWITCH, name = "Dungeon Death Message",
            description = "Displays a funny death message when someone in your party dies.",
            category = "Dungeon", subcategory = "Funny stuff",
            searchTags = {"Message", "Dead"}
    )
    public static boolean dungeonDeathMessage = false;

    @Property(
            type = PropertyType.TEXT, name = "Death Message Text",
            description = "This text will be sent when someone dies, leaving this \nempty will cause it to for example say \"Good night\" when someone dies\n§cDungeon Death Message must be enabled for it to work.",
            category = "Dungeon", subcategory = "Funny stuff",
            searchTags = {"Dead"}
    )

    public static String dungeonDeathMessageText = "";

    public static GoblinConfig INSTANCE = new GoblinConfig();

    public GoblinConfig() {
        super(new File("./config/goblinmod.toml"), "GoblinsMod (" + GoblinsMod.VERSION + ")");
        initialize();
    }

}
