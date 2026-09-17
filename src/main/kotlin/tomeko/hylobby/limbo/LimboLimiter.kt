package tomeko.hylobby.limbo

//? if 1.8.9 {
//import net.minecraft.client.Minecraft
//?} else {
import net.minecraft.util.Util
//?}
import org.polyfrost.oneconfig.api.event.v1.events.HypixelLocationEvent
import org.polyfrost.oneconfig.api.event.v1.invoke.impl.Subscribe
import kotlin.math.min

object LimboLimiter {
    var limboJoinTime = -1L

    fun getFramerateLimit(original: Int, framerateLimit: Int): Int {
        val millis =
            //? if 1.8.9 {
            //Minecraft.getSystemTime()
            //?} else {
            Util.getMillis()
        //?}
        return if (limboJoinTime == -1L) {
            original
        } else if (millis - limboJoinTime > 600L * 1000L) {
            10 // matches the vanilla LONG_AFK throttle
        } else if (millis - limboJoinTime > 5L * 1000L) {
            min(framerateLimit, 30) // matches the vanilla SHORT_AFK throttle
        } else {
            original
        }
    }

    @Subscribe
    fun onLocationUpdate(event: HypixelLocationEvent) {
        val millis =
            //? if 1.8.9 {
            //Minecraft.getSystemTime()
        //?} else {
        Util.getMillis()
        //?}
        limboJoinTime = if (event.location.serverName.orElse(null) == "limbo") {
            millis
        } else {
            -1L
        }
    }
}