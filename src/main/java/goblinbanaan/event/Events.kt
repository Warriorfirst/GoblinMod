package goblinbanaan.event

import net.minecraft.block.state.IBlockState
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.model.ModelBase
import net.minecraft.entity.EntityLivingBase
import net.minecraft.inventory.Container
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import net.minecraft.network.Packet
import net.minecraft.network.play.server.S21PacketChunkData
import net.minecraft.util.BlockPos
import net.minecraft.util.EnumFacing
import net.minecraftforge.fml.common.eventhandler.Cancelable
import net.minecraftforge.fml.common.eventhandler.Event

/**
 * Taken from Skyblock Client under GNU Affero General Public License v3.0
 * https://github.com/Harry282/Skyblock-Client/blob/main/LICENSE
 * @author Harry282
 */

open class ClickEvent : Event() {
    @Cancelable
    class LeftClickEvent : ClickEvent()

    @Cancelable
    class RightClickEvent : ClickEvent()
}

open class GuiContainerEvent(val container: Container, val gui: GuiContainer) : Event() {
    @Cancelable
    class DrawSlotEvent(container: Container, gui: GuiContainer, var slot: Slot) :
        GuiContainerEvent(container, gui)

    @Cancelable
    class SlotClickEvent(container: Container, gui: GuiContainer, var slot: Slot?, var slotId: Int) :
        GuiContainerEvent(container, gui)
}

open class MovementUpdateEvent : Event() {
    @Cancelable
    class Pre : MovementUpdateEvent()

    @Cancelable
    class Post : MovementUpdateEvent()
}


@Cancelable
class RenderLivingEntityEvent(
    var entity: EntityLivingBase,
    var p_77036_2_: Float,
    var p_77036_3_: Float,
    var p_77036_4_: Float,
    var p_77036_5_: Float,
    var p_77036_6_: Float,
    var scaleFactor: Float,
    var modelBase: ModelBase
) : Event()

/**
 * Taken from Stonk Delay under GNU LESSER GENERAL PUBLIC LICENSE v3.0
 * https://github.com/appable0/StonkDelay/blob/main/LICENSE
 * @author Appable
 */

@Cancelable
class ReceivePacketEvent(val packet: Packet<*>) : Event()

class BlockBreakEvent(val pos: BlockPos, val side: EnumFacing): Event()

@Cancelable
class BlockChangeEvent(val pos: BlockPos, val state: IBlockState): Event()

class ChunkUpdateEvent(val packet: S21PacketChunkData): Event()

class BlockPlaceEvent(val pos: BlockPos, val stack: ItemStack?): Event()
