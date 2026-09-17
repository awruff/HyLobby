package tomeko.hylobby.sounds

import org.polyfrost.oneconfig.api.event.v1.invoke.impl.Subscribe
import org.polyfrost.oneconfig.api.hypixel.v1.HypixelUtils
import tomeko.hylobby.config.HyLobbyConfig

object SilentLobby {
    @Subscribe
    fun onSoundPlay(event: SoundPlayEvent) {
        if (HypixelUtils.getLocation().inGame()) return

        val path =
            //? if 1.8.9 {
            //event.sound.soundLocation.resourcePath
            //?} else {
            event.sound.identifier.path
            //?}
        if (HyLobbyConfig.silentLobby && !path.startsWith("ui.")) {
            event.cancelled = true
        } else {
            if (DisableSoundRule.entries.any { it.shouldDisable(path) }) {
                event.cancelled = true
            }
        }
    }

    private enum class DisableSoundRule(
        val matches: (String) -> Boolean,
        val isEnabled: () -> Boolean
    ) {
        STEPPING({ it.endsWith(".step") }, { HyLobbyConfig.lobbyDisableSteppingSounds }),
        SLIME({ it.startsWith("entity.slime") }, { HyLobbyConfig.lobbyDisableSlimeSounds }),
        DRAGON({ it.startsWith("entity.ender_dragon") }, { HyLobbyConfig.lobbyDisableDragonSounds }),
        WITHER({ it.startsWith("entity.wither") }, { HyLobbyConfig.lobbyDisableWitherSounds }),
        ITEM_PICKUP({ it == "entity.item.pickup" }, { HyLobbyConfig.lobbyDisableItemPickupSounds }),
        EXPERIENCE_ORB({ it == "entity.experience_orb.pickup" }, { HyLobbyConfig.lobbyDisableExperienceOrbSounds }),
        PRIMED_TNT({ it == "entity.tnt.primed" }, { HyLobbyConfig.lobbyDisablePrimedTntSounds }),
        EXPLOSION({ it == "entity.generic.explode" }, { HyLobbyConfig.lobbyDisableExplosionSounds }),
        DELIVERY_MAN({ it == "entity.chicken.egg" }, { HyLobbyConfig.lobbyDisableDeliveryManSounds }),
        NOTEBLOCK({ it.startsWith("block.note_block") }, { HyLobbyConfig.lobbyDisableNoteBlockSounds }),
        FIREWORK({ it.startsWith("entity.firework_rocket") }, { HyLobbyConfig.lobbyDisableFireworkSounds }),
        LEVEL_UP({ it == "entity.player.levelup" }, { HyLobbyConfig.lobbyDisableLevelupSounds }),
        ARROW({ it.startsWith("entity.arrow") }, { HyLobbyConfig.lobbyDisableArrowSounds }),
        BAT({ it.startsWith("entity.bat") }, { HyLobbyConfig.lobbyDisableBatSounds }),
        FIRE({ it.startsWith("block.fire") }, { HyLobbyConfig.lobbyDisableFireSounds }),
        ENDERMAN({ it.startsWith("entity.enderman") }, { HyLobbyConfig.lobbyDisableEndermanSounds }),
        DOOR({ it.startsWithAny("block.wooden_door", "block.wooden_trapdoor", "block.iron_door", "block.iron_trapdoor") }, { HyLobbyConfig.lobbyDisableDoorSounds }),
        PORTAL({ it.startsWith("block.portal") }, { HyLobbyConfig.lobbyDisablePortalSounds });

        fun shouldDisable(path: String): Boolean = isEnabled() && matches(path)
    }

    private fun String.startsWithAny(vararg prefixes: String): Boolean {
        return prefixes.any { this.startsWith(it) }
    }
}