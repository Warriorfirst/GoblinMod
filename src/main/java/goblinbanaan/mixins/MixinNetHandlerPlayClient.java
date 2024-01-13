package goblinbanaan.mixins;

import goblinbanaan.event.BlockPlaceEvent;
import goblinbanaan.event.ChunkUpdateEvent;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.server.S21PacketChunkData;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Taken from Stonk Delay under GNU LESSER GENERAL PUBLIC LICENSE v3.0
 * https://github.com/appable0/StonkDelay/blob/main/LICENSE
 * @author Appable
 */

@Mixin(NetHandlerPlayClient.class)
public class MixinNetHandlerPlayClient {
    @Inject(method = "handleChunkData", at = @At("TAIL"))
    private void onChunkDataUpdate(S21PacketChunkData packetIn, CallbackInfo ci) {
        MinecraftForge.EVENT_BUS.post(new ChunkUpdateEvent(packetIn));
    }

    @Inject(method = "addToSendQueue", at = @At("TAIL"))
    private void onBlockPlace(Packet<?> packet, CallbackInfo ci) {
        if (packet instanceof C08PacketPlayerBlockPlacement) {
            C08PacketPlayerBlockPlacement blockPacket = (C08PacketPlayerBlockPlacement) packet;
            Vec3i vector = EnumFacing.getFront(blockPacket.getPlacedBlockDirection()).getDirectionVec();
            BlockPos position = blockPacket.getPosition().add(vector);
            MinecraftForge.EVENT_BUS.post(new BlockPlaceEvent(position, blockPacket.getStack()));
        }
    }
}