package goblinbanaan.features.miscellaneous

import goblinbanaan.utils.Utils.dungeonStarted
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class AutoLeap {

    @SubscribeEvent
    fun message(event: ClientChatReceivedEvent) {
        if (dungeonStarted()) {
            println("Dungeon started")
        }
    }




}