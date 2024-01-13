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
            description = "This text will be sent when someone dies, leaving this \nempty will cause it to for example say \"Good night\" when someone dies.",
            category = "Dungeon", subcategory = "Funny stuff",
            searchTags = {"Dead"}
    )
    public static String dungeonDeathMessageText = "";

    @Property(
            type = PropertyType.SWITCH, name = "Dungeon Join Commands",
            description = "Allows your party to send text to queue into dungeons.\nThe commands are !f1,f2,f3 etc. till !m7.\n§cCommand only triggers if it got typed in Party Chat.",
            category = "Dungeon", subcategory = "Party commands",
            searchTags = {"Party", "Command", "Commands"}
    )
    public static boolean queueCommands = false;

    @Property(
            type = PropertyType.SWITCH, name = "Fire Freeze Timer",
            description = "This displays a title screen to let you know when to use the Fire Freeze in F3/M3\n§cSkyblock Addons' boss message hider must be disabled for this to work.",
            category = "Dungeon", subcategory = "Miscellaneous",
            searchTags = {"ff", "M3", "F3", "Professor"}
    )
    public static boolean fireFreezeTimer = false;

    @Property(
            type = PropertyType.SWITCH, name = "Heal reminder",
            description = "This will display a title on screen if your HP is lower than a certain percentage.",
            category = "Miscellaneous", subcategory = "Reminders",
            searchTags = {"hp", "low"}
    )
    public static boolean healReminder = false;

    @Property(
            type = PropertyType.PERCENT_SLIDER, name = "Health Percentage",
            description = "Set a certain percentage value so that a title will pop up once your HP is lower than the value.",
            category = "Miscellaneous", subcategory = "Reminders",
            searchTags = {"%", "hp"}

    )
    public static float healthPercentage = 0;

    @Property(
            type = PropertyType.SWITCH, name = "Quick Close Chest",
            description = "This will close the chest in dungeons if you get an item drop by pressing any key.",
            category = "Dungeon", subcategory = "Miscellaneous",
            searchTags = {"open", "close", "secret"}
    )
    public static boolean quickCloseChest = true;

    @Property(
            type = PropertyType.SWITCH, name = "Auto Melody",
            description = "Will try to complete your melody.",
            category = "Miscellaneous", subcategory = "Auto",
            searchTags = {"Hair", "Auto"}
    )
    public static boolean autoMelody = false;

    @Property(
            type = PropertyType.SWITCH, name = "Auto Soul Whip",
            description = "Holding down right click on soulwhip will cause it to click between certain CPS.\n§cThis is currently broken! DO NOT ENABLE.",
            category = "Miscellaneous", subcategory = "Auto",
            searchTags = {"ac", "autoclicker"}
    )
    public static boolean autoSoulWhip = false;

    @Property(
            type = PropertyType.SWITCH, name = "G Key",
            description = "Hold a certain key to make blocks turn into thin air.\n§cChange the hotkey in your minecraft settings. Esc -> Options -> Controls.",
            category = "Dungeon", subcategory = "Ghost Block",
            searchTags = {"gkey", "stonk"}
    )
    public static boolean gKey = false;

    @Property(
            type = PropertyType.SWITCH, name = "Ghost Block",
            description = "Hold right-click on a pickaxe to make it a ghost block.",
            category = "Dungeon", subcategory = "Ghost Block",
            searchTags = {"gkey", "invis", "stonk"}
    )
    public static boolean ghostBlock = false;

    @Property(
            type = PropertyType.SWITCH, name = "Disable Mining Cooldown",
            description = "Disables messages such as \"This ability is on cooldown for 6s\", \"Mining Speed Boost is now available!\" if you right click on a pickaxe in the air while ghost blocking.",
            category = "Dungeon", subcategory = "Ghost Block",
            searchTags = {"stonk"}
    )
    public static boolean disableMiningCooldown = false;

    @Property(
            type = PropertyType.SWITCH, name = "Safe Ghost Blocking",
            description = "This will mimic a left click when holding right click to ghost block.\n§6Highly recommended to turn this on.",
            category = "Dungeon", subcategory = "Ghost Block",
            searchTags = {"stonk", "ready"}
    )
    public static boolean safeGhostBlock = false;

    @Property(
            type = PropertyType.SWITCH, name = "Stonkdelay",
            description = "Broken blocks will be ghostblocked for a certain duration\n§cThis will only work if you have Ghost Block disabled",
            category = "Dungeon", subcategory = "Ghost Block"
    )
    public static boolean stonkDelay = false;

    @Property(
            type = PropertyType.SLIDER, name = "Set Ghost Block Duration",
            description = "Set a certain duration before the blocks appear again.\nSetting this to 0 disables it.\nSetting this to 60000 will make it last permanently.",
            category = "Dungeon", subcategory = "Ghost Block",
            min = 0,
            max = 60000
    )
    public static int stonkDelayDuration = 0;
    @Property(
            type = PropertyType.SWITCH, name = "Bonzo Timer",
            description = "Bonzo Mask timer to help with pre 4 in M7 and F7",
            category = "Dungeon", subcategory = "Miscellaneous",
        searchTags = {"helper", "pre4"}
    )
    public static boolean bonzoTimer = false;

    @Property(
            type = PropertyType.SWITCH, name = "Phoenix Timer",
            description = "Phoenix Pet timer to help with pre 4 in M7 and F7",
            category = "Dungeon", subcategory = "Miscellanous",
            searchTags = {"helper", "pre4"}
    )
    public static boolean phoenixTimer = false;
    






    
    public static GoblinConfig INSTANCE = new GoblinConfig();

    public GoblinConfig() {
        super(new File("./config/goblinmod.toml"), "GoblinsMod (" + GoblinsMod.VERSION + ")");
        addDependency("healthPercentage","healReminder");
        addDependency("dungeonDeathMessageText","dungeonDeathMessage");
        addDependency("disableMiningCooldown","ghostBlock");
        addDependency("safeGhostBlock","ghostBlock");
        addDependency("stonkDelayDuration","stonkDelay");
        initialize();
    }

}
