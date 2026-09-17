package tomeko.hylobby.mixins;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
//? if 1.8.9 {
//import net.minecraft.client.Minecraft;
//?} else {
import com.mojang.blaze3d.platform.FramerateLimitTracker;
//?}
import org.spongepowered.asm.mixin.Mixin;
//? if fabric {
import org.spongepowered.asm.mixin.Shadow;
//?}
import org.spongepowered.asm.mixin.injection.At;
import tomeko.hylobby.config.HyLobbyConfig;
import tomeko.hylobby.limbo.LimboLimiter;

@Mixin(
        //? if 1.8.9 {
        //Minecraft.class
        //?} else {
        FramerateLimitTracker.class
        //?}
)
public abstract class FramerateLimitTrackerMixin_LimboLimiter {
    //? if fabric {
    @Shadow private int framerateLimit;
    //?}

    @ModifyReturnValue(
            method =
                    //? if 1.8.9 {
                    //"getLimitFramerate",
                    //?} else {
                    "getFramerateLimit",
            //?}
            at = @At("RETURN")
    )
    private int limitLimboFramerate(int original) {
        if (HyLobbyConfig.INSTANCE.getLimboLimiter()) {
            return LimboLimiter.INSTANCE.getFramerateLimit(
                    original,
                    //? if 1.8.9 {
                    //Minecraft.getMinecraft().gameSettings.limitFramerate
                    //?} else {
                    this.framerateLimit
                    //?}
            );
        }

        return original;
    }
}