/**
 * Modified from SkyblockFeatures under GNU Lesser General Public License v3.0
 * https://github.com/MrFast-js/SkyblockFeatures/blob/master/LICENSE
 * @author MrFast-js
 */

package goblinbanaan.utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import com.google.gson.JsonObject;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

public class Utils {

    private static final Minecraft mc = Minecraft.getMinecraft();

    public static boolean inDungeons = false;

    public static final NumberFormat nf = NumberFormat.getInstance(Locale.US);

    private static final String[] steps = new String[] {"", "K", "M", "B","T"};


    /**
     * Taken from SkyblockFeatures under GPL 3.0 license
     * https://github.com/MrFast-js/SkyblockFeatures/blob/main/LICENSE
     * @author MrFast-js
     */
    public static String shortenNumber(double number) {
        if(number<1000) {
            return ((int) number)+"";
        }
        int magnitudeIndex = 0;

        while (number >= 1000) {
            magnitudeIndex++;
            number /= 1000;
        }

        String formattedNumber;

        if (magnitudeIndex > 0 && Math.floor(number) == number) {
            formattedNumber = String.valueOf((int) number);
        } else {
            formattedNumber = round(number, 1);
        }

        return formattedNumber + steps[magnitudeIndex];
    }

    /*
    Returns a int if it exists and 0 if it doesnt.
    Useful for /pv when certain APIs are turned off
     */
    public static int safeGetInt(JsonObject jsonObject, String key) {
        try {
            return jsonObject.get(key).getAsInt();
        } catch (Exception ignored) {
            return 0; // Default value when an exception occurs
        }
    }
    public static long safeGetLong(JsonObject jsonObject, String key) {
        try {
            return jsonObject.get(key).getAsLong();
        } catch (Exception ignored) {
            return 0L; // Default value when an exception occurs
        }
    }

    public static String percentOf(long num, long outOf) {
        double percent = (double) num / outOf * 100;
        return ChatFormatting.DARK_GRAY + " (" + String.format("%.2f", percent) + "%)";
    }

    public static boolean isOnHypixel() {
        try {
            if (mc != null && mc.theWorld != null && !mc.isSingleplayer()) {
                if (mc.thePlayer != null && mc.thePlayer.getClientBrand() != null) {
                    if (mc.thePlayer.getClientBrand().toLowerCase().contains("hypixel")) return true;
                }
                if (mc.getCurrentServerData() != null) return mc.getCurrentServerData().serverIP.toLowerCase().contains("hypixel");
            }
            return false;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Taken from Danker's Skyblock Mod under GPL 3.0 license
     * https://github.com/bowser0000/SkyblockMod/blob/master/LICENSE
     * @author bowser0000
     */
    public static boolean checkForSkyblock() {
        if (isOnHypixel()) {
            if(mc.theWorld.getScoreboard() == null) return false;
            ScoreObjective scoreboardObj = mc.theWorld.getScoreboard().getObjectiveInDisplaySlot(1);
            if (scoreboardObj != null) {
                String scObjName = ScoreBoardUtil.fixFormatting(scoreboardObj.getDisplayName(), true);
                if (scObjName.contains("SKYBLOCK")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String cleanColor(String in) {
        return in.replaceAll("(?i)\\u00A7.", "");
    }

    public static Minecraft GetMC() {
        return mc;
    }

    public static boolean overrideDevMode = false;
    public static boolean overrideDevModeValue = false;
    public static boolean isDeveloper() {
        String[] developers = {"Skyblock_Lobby"};
        if(Utils.GetMC().thePlayer==null) return false;
        boolean dev = Arrays.asList(developers).contains(Utils.GetMC().thePlayer.getName());
        if(overrideDevMode) {
            dev = overrideDevModeValue;
        }
        return dev;
    }

    public static String convertToTitleCase(String input) {
        String[] words = input.split("_");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (!word.isEmpty()) {
                String firstCharacter = word.substring(0, 1);
                String remainingCharacters = word.substring(1).toLowerCase();

                if (i > 0) {
                    result.append(" ");
                }

                result.append(firstCharacter.toUpperCase()).append(remainingCharacters);
            }
        }
        return result.toString();
    }

    static int[] dungeonsXPPerLevel = {0, 50, 75, 110, 160, 230, 330, 470, 670, 950, 1340, 1890, 2665, 3760, 5260, 7380, 10300, 14400,
            20000, 27600, 38000, 52500, 71500, 97000, 132000, 180000, 243000, 328000, 445000, 600000, 800000,
            1065000, 1410000, 1900000, 2500000, 3300000, 4300000, 5600000, 7200000, 9200000, 12000000, 15000000,
            19000000, 24000000, 30000000, 38000000, 48000000, 60000000, 75000000, 93000000, 116250000};

    public static double xpToDungeonsLevel(double xp) {
        for (int i = 0, xpAdded = 0; i < dungeonsXPPerLevel.length; i++) {
            xpAdded += dungeonsXPPerLevel[i];
            if (xp < xpAdded) {
                double level =  (i - 1) + (xp - (xpAdded - dungeonsXPPerLevel[i])) / dungeonsXPPerLevel[i];
                return (double) Math.round(level * 100) / 100;
            }
        }
        return 50D;
    }

    public static double randomNumber(int min,int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static String secondsToTime(long seconds) {
        String time = "";
        long sec = seconds % 60;
        long min = (seconds / 60) % 60;
        long hours = (seconds / 3600) % 24;
        long days = seconds / (3600 * 24);

        if (days > 0) time += days + "d ";
        if (hours > 0) time += hours + "h ";
        if (min > 0) time += min + "m ";
        time += sec + "s";

        return time.trim();
    }

    // Precision is # of decimal places
    public static String round(double value, int precision) {
        double scale = Math.pow(10, precision);
        double roundedValue = Math.round(value * scale) / scale;
        return String.valueOf(roundedValue);
    }

    public static String[] getListOfPlayerUsernames() {
        final Collection<NetworkPlayerInfo> players = Utils.GetMC().getNetHandler().getPlayerInfoMap();
        final List<String> list = new ArrayList<>();
        for (final NetworkPlayerInfo info : players) {
            if(!info.getGameProfile().getName().contains("!")) list.add(info.getGameProfile().getName());
        }
        return list.toArray(new String[0]);
    }

    /**
     * Taken from Danker's Skyblock Mod under GPL 3.0 license
     * https://github.com/bowser0000/SkyblockMod/blob/master/LICENSE
     * @author bowser0000
     */
    public static boolean checkForDungeons() {
        if (checkForSkyblock()) {
            List<String> scoreboard = ScoreBoardUtil.getSidebarLines();
            for (String s : scoreboard) {
                if ((s.contains("The Catacombs") && !s.contains("Queue")) || s.contains("Cleared:")) {
                    inDungeons = true;
                    return true;
                }
            }
        }
        inDungeons = false;
        return false;
    }

    public static boolean dungeonStarted() {
        if (checkForSkyblock()) {
            List<String> scoreboard = ScoreBoardUtil.getSidebarLines();
            for (String s : scoreboard) {
                if ((s.contains("The Catacombs") && !s.contains("Queue")) && s.contains("Cleared:")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String floorName() {
        if (checkForDungeons()) {
            List<String> scoreboard = ScoreBoardUtil.getSidebarLines();
            for (String s : scoreboard) {
                if (s.contains("The Catacombs (F1)")) {
                    return "F1";
                } else if (s.contains("The Catacombs (F2)")) {
                    return "F2";
                } else if (s.contains("The Catacombs (F3)")) {
                    return "F3";
                } else if (s.contains("The Catacombs (F4)")) {
                    return "F4";
                } else if (s.contains("The Catacombs (F5)")) {
                    return "F5";
                } else if (s.contains("The Catacombs (F6)")) {
                    return "F6";
                } else if (s.contains("The Catacombs (F7)")) {
                    return "F7";
                } else if (s.contains("The Catacombs (M1)")) {
                    return "M1";
                } else if (s.contains("The Catacombs (M2)")) {
                    return "M2";
                } else if (s.contains("The Catacombs (M3)")) {
                    return "M3";
                } else if (s.contains("The Catacombs (M4)")) {
                    return "M4";
                } else if (s.contains("The Catacombs (M5)")) {
                    return "M5";
                } else if (s.contains("The Catacombs (M6)")) {
                    return "M6";
                } else if (s.contains("The Catacombs (M7)")) {
                    return "M7";
                }
            }
        }
        return "Couldn't detect floor";
    }

    public static String detectDungeonClass(String playerName) {
        if (dungeonStarted()) {
            List<String> playerList = PlayerListUtil.getTabList();

            for (String entry : playerList) {
                if (entry.contains(playerName) && entry.contains("(Mage ")) {
                    return "Mage";
                } else if (entry.contains(playerName) && entry.contains("(Healer ")) {
                    return "Healer";
                } else if (entry.contains(playerName) && entry.contains("(Berserk ")) {
                    return "Berserk";
                } else if (entry.contains(playerName) && entry.contains("(Tank ")) {
                    return "Tank";
                } else if (entry.contains(playerName) && entry.contains("(Archer ")) {
                    return "Archer";
                }
            }
        }
        return "Not in dungeon";
    }

    public static void sendMessage(String string) {
        if (Utils.GetMC().ingameGUI != null || Utils.GetMC().thePlayer == null) {
            Utils.GetMC().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText("[§b[§3GoblinsMod§b] "+ChatFormatting.RESET+string));
        }
    }


    public static void playSound(String sound, double pitch) {
        mc.thePlayer.playSound(sound, 1, (float) pitch);
    }

    public static boolean isNPC(Entity entity) {
        if(entity instanceof EntityPlayer) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            return entity.getUniqueID().version() == 2 && entityLivingBase.getHealth() == 20.0F && !entityLivingBase.isPlayerSleeping() && Utils.checkForSkyblock();
        } else return false;
    }

    public static void setTimeout(Runnable code, int ms) {
        setTimeout(code,ms,false);
    }
    public static void setTimeout(Runnable code, int ms,boolean addToMinecraft) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(addToMinecraft) Utils.GetMC().addScheduledTask(code);
                else code.run();
            }
        }, ms);
    }

    public static String convertIdToLocation(String id) {
        switch (id) {
            case "dynamic":
                return "Private Island";
            case "winter":
                return "Jerry's Workshop";
            case "mining_1":
                return "Gold Mine";
            case "mining_2":
                return "Deep Caverns";
            case "mining_3":
                return "Dwarven Mines";
            case "combat_1":
                return "Spider's Den";
            case "combat_3":
                return "The End";
            case "farming_1":
                return "The Farming Islands";
            case "foraging_1":
                return "The Park";
            default:
                return Utils.convertToTitleCase(id);
        }
    }

    public static void drawTitle(String title, String subtitle, int ticks) {
        Minecraft.getMinecraft().ingameGUI.displayTitle(null, null, 0, ticks, 0);
        Minecraft.getMinecraft().ingameGUI.displayTitle(title, null, -1, -1, -1);
        Minecraft.getMinecraft().ingameGUI.displayTitle(null, subtitle, -1, -1, -1);
    }

    public static int getRandomNumberInRange (int number1, int number2) {
        Random random = new Random();
        return random.nextInt((number2 - number1) + 1) + number1;
    }
}