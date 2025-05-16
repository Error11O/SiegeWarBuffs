package dev.Error110.swBuffs.Listener

import com.gmail.goosius.siegewar.SiegeWarAPI
import com.palmergames.bukkit.towny.event.time.NewShortTimeEvent
import dev.Error110.swBuffs.Buffs.buffManager
import dev.Error110.swBuffs.Objects.Voting
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.util.logging.Logger

class inZoneListener(internal val plugin: Plugin, internal val logger: Logger) : Listener {

    @EventHandler
    fun isInZone(event: NewShortTimeEvent) {
        if (!SiegeWarAPI.isBattleSessionActive()) return
        for (player in Bukkit.getOnlinePlayers()) {
            if (!SiegeWarAPI.isLocationInActiveSiegeZone(player.location)) return
            buffManager().buffAction(Voting.buffName, player, plugin)
        }
    }
}