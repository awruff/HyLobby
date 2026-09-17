package tomeko.hylobby.mixins;

//? if 1.8.9 {
/*import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
*///?} else {
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
//?}
import org.polyfrost.oneconfig.api.event.v1.EventManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
//? if 1.8.9 {
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//?} else {
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//?}
import tomeko.hylobby.sounds.SoundPlayEvent;

@Mixin(
        //? if 1.8.9 {
        //SoundManager.class
        //?} else {
        SoundEngine.class
        //?}
)
public abstract class SoundEngineMixin_SoundPlayEvent {
    @Inject(
            method =
                    //? if 1.8.9 {
                    //"playSound",
                    //?} else {
                    "play",
                    //?}
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventLobbyMusic(
            //? if 1.8.9 {
            //ISound instance, CallbackInfo ci
            //?} else {
            SoundInstance instance, CallbackInfoReturnable<SoundEngine.PlayResult> cir
            //?}
    ) {
        SoundPlayEvent event = new SoundPlayEvent(instance);
        EventManager.INSTANCE.post(event);
        if (event.cancelled) {
            //? if 1.8.9 {
            //ci.cancel();
            //?} else {
            cir.setReturnValue(SoundEngine.PlayResult.NOT_STARTED);
            //?}
        }
    }
}