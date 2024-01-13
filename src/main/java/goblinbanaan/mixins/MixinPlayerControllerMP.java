package goblinbanaan.mixins;

import goblinbanaan.event.BlockBreakEvent;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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

@Mixin(PlayerControllerMP.class)
public class MixinPlayerControllerMP {
    @Inject(method = "onPlayerDestroyBlock", at = @At("HEAD"))
    private void onBlockBreak(BlockPos pos, EnumFacing side, CallbackInfoReturnable<Boolean> cir) {
        MinecraftForge.EVENT_BUS.post(new BlockBreakEvent(pos, side));
    }
}