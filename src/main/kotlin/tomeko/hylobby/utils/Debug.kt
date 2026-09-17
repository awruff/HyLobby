package tomeko.hylobby.utils

import tomeko.hylobby.config.HyLobbyConfig

//? if fabric {
import org.slf4j.Logger
import org.slf4j.LoggerFactory
//?}

object Debug {
    //? if fabric {
    private val LOGGER: Logger = LoggerFactory.getLogger(Constants.MOD_ID)
    //?}

    fun log(message: String) {
        if (!HyLobbyConfig.debugModeEnabled) return

        forceLog(message)
    }

    fun forceLog(message: String) {
        //? if 1.8.9 {
        //println("[${Constants.MOD_NAME}] $message")
        //?} else {
        LOGGER.info("[${Constants.MOD_NAME}] $message")
        //?}
    }
}