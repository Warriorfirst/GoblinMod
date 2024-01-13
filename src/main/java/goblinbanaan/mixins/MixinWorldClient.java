package goblinbanaan.mixins;

import goblinbanaan.event.BlockChangeEvent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Taken from Stonk Delay under GNU LESSER GENERAL PUBLIC LICENSE v3.0
 * https://github.com/appable0/StonkDelay/blob/main/LICENSE
 * @author Appable
 */

@Mixin(WorldClient.class)
public class MixinWorldClient {

    @Inject(method = "invalidateRegionAndSetBlock", at = @At("HEAD"), cancellable = true)
    private void onInvalidateRegionAndSetBlock(BlockPos pos, IBlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (MinecraftForge.EVENT_BUS.post(new BlockChangeEvent(pos, state))) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

}