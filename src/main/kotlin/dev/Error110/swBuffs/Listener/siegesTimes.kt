package dev.Error110.swBuffs.Listener

import com.gmail.goosius.siegewar.events.BattleSessionEndedEvent
import com.gmail.goosius.siegewar.events.BattleSessionStartedEvent
import dev.Error110.swBuffs.Buffs.buffManager
import dev.Error110.swBuffs.Objects.Voting
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin
import java.util.logging.Logger

class siegesTimes(internal val plugin: Plugin, internal val logger: Logger) : Listener {

    @EventHandler
    fun preSwStart(event: BattleSessionStartedEvent) {
        val cata = Voting.totalVote()
        val buff = buffManager().cataToBuff(cata.title).toString()
        Bukkit.broadcastMessage("${ChatColor.GREEN}Category ${cata.title} won with ${cata.value} votes! Buff $buff was randomly selected.")
        Voting.voteToggle = false
        Voting.buffName = buff
    }

    @EventHandler
    fun swEnd(event: BattleSessionEndedEvent) {
        val item = Voting.totalVote()
        Bukkit.broadcastMessage("${ChatColor.GREEN}Buff ${item.title} is now over! vote is now on for next session.")
        Voting.voteToggle = true
        Voting.buffName = ""
    }

}