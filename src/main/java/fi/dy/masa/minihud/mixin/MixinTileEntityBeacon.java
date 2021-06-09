package fi.dy.masa.minihud.mixin;

import fi.dy.masa.minihud.renderer.OverlayRendererBeaconRange;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.block.entity.BeaconBlockEntity.class)
public abstract class MixinTileEntityBeacon
{
    @Shadow private int level;

    private int levelsPre;

    @Inject(method = "updateLevel", at = @At("HEAD"))
    private void onUpdateSegmentsPre(int x, int y, int z, CallbackInfo ci)
    {
        this.levelsPre = this.level;
    }

    @Inject(method = "updateLevel", at = @At("RETURN"))
    private void onUpdateSegmentsPost(int x, int y, int z, CallbackInfo ci)
    {
        if (this.level != this.levelsPre)
        {
            OverlayRendererBeaconRange.setNeedsUpdate();
        }
    }
}
