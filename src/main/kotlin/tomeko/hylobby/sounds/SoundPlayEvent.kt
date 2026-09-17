package tomeko.hylobby.sounds

//? if 1.8.9 {
//import net.minecraft.client.audio.ISound
//?} else {
import net.minecraft.client.resources.sounds.SoundInstance
//?}
import org.polyfrost.oneconfig.api.event.v1.events.Event

data class SoundPlayEvent(
    //? if 1.8.9 {
    //val sound: ISound
    //?} else {
    val sound: SoundInstance
    //?}
) : Event.Cancellable()