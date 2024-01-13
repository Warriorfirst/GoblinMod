package goblinbanaan.utils

import goblinbanaan.GoblinsMod.mc
import net.minecraft.client.settings.KeyBinding

object PlayerUtil {

    fun rightClick() {
        KeyBinding.onTick(mc.gameSettings.keyBindUseItem.keyCode)
    }

    fun leftClick() {
        KeyBinding.onTick(mc.gameSettings.keyBindAttack.keyCode)
    }
}