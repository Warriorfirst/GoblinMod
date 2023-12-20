package goblinbanaan.utils

import com.google.common.collect.ComparisonChain
import com.google.common.collect.Ordering
import net.minecraft.client.Minecraft
import net.minecraft.client.network.NetworkPlayerInfo
import net.minecraft.world.WorldSettings
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

object PlayerListUtil {

    /**
     * Taken from Skyhanni
     * Source: https://github.com/hannibal002/SkyHanni
     *
     * @author hannibal002
     */
    private val playerOrdering = Ordering.from(PlayerComparator())

    @SideOnly(Side.CLIENT)
    internal class PlayerComparator : Comparator<NetworkPlayerInfo> {
        override fun compare(o1: NetworkPlayerInfo, o2: NetworkPlayerInfo): Int {
            val team1 = o1.playerTeam
            val team2 = o2.playerTeam
            return ComparisonChain.start().compareTrueFirst(
                o1.gameType != WorldSettings.GameType.SPECTATOR,
                o2.gameType != WorldSettings.GameType.SPECTATOR
            )
                .compare(
                    if (team1 != null) team1.registeredName else "",
                    if (team2 != null) team2.registeredName else ""
                )
                .compare(o1.gameProfile.name, o2.gameProfile.name).result()
        }
    }

    /**
     * Taken from Skyhanni
     * Source: https://github.com/hannibal002/SkyHanni
     *
     * @author hannibal002
     */
    @JvmStatic
    fun getTabList(): List<String> {
        val players = playerOrdering.sortedCopy(Minecraft.getMinecraft().thePlayer.sendQueue.playerInfoMap)
        val result: MutableList<String> = ArrayList()

        for (info in players) {
            val name = Minecraft.getMinecraft().ingameGUI.tabList.getPlayerName(info)
            result.add(stripColorCodes(name))

        }
        return result
    }

    private fun stripColorCodes(string: String): String {
        return string.replace("§[a-z, 1-9]".toRegex(), "")
    }
}