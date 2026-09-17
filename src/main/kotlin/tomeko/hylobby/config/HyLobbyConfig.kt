package tomeko.hylobby.config

//? if forge {
/*import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.*
import cc.polyfrost.oneconfig.config.core.OneColor as PolyColor
import cc.polyfrost.oneconfig.config.data.InfoType
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
*///?} else {
import org.polyfrost.compose.render.PolyColor
import org.polyfrost.oneconfig.api.config.v1.Config
import org.polyfrost.oneconfig.api.config.v1.annotations.*
//?}
//? if forge {
//import tomeko.hylobby.hud.BedwarsResourceDisplay
//?}
import tomeko.hylobby.utils.Constants

object HyLobbyConfig : Config(
    //? if forge {
    /*Mod(
        Constants.MOD_NAME,
        ModType.HYPIXEL,
        Constants.MOD_ICON
    ),
    "${Constants.MOD_ID}.json"
    *///?} else {
    "${Constants.MOD_ID}.json",
    Constants.MOD_ICON,
    Constants.MOD_NAME,
    Category.HYPIXEL
    //?}
) {
    //? if !forge {
    val DEPENDENCIES: List<Pair<String, List<String>>> = listOf(
    )
    //?}

    fun register() {
        //? if forge {
        //initialize()
        //?} else {
        preload()
        for ((condition, dependencies) in DEPENDENCIES) {
            for (dependency in dependencies) {
                addDependency(dependency, condition)
            }
        }
        //?}
    }

    //? if forge {
    //@Exclude
    //?}
    private const val CATEGORY_MIDDLE_CLICK_GUI_ITEMS = "Middle Click GUI Items"

    @Switch(
        //? if forge {
        //name =
        //?} else {
        title =
        //?}
        "Middle Click GUI Items",
        description = "Replace left click with middle click in GUIs in Hypixel lobbies",
        category = CATEGORY_MIDDLE_CLICK_GUI_ITEMS
    )
    var middleClickInLobby = true


    //? if forge {
    //@Exclude
    //?}
    private const val CATEGORY_DEBUG = "Debug"

    @Info(
        //? if forge {
        //text =
        //?} else {
        title =
        //?}
        "Probably should stay disabled",
        //? if forge {
        //type = InfoType.WARNING,
        //?}
        category = CATEGORY_DEBUG
    )
    var debugModeInfo: Nothing? = null

    @Switch(
        //? if forge {
        //name =
        //?} else {
        title =
        //?}
        "Debug Mode",
        category = CATEGORY_DEBUG
    )
    var debugModeEnabled = false
}