package dev.Error110.swBuffs.Commands

import com.gmail.goosius.siegewar.SiegeWarAPI
import dev.Error110.swBuffs.Objects.Voting
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player

class buffVote : CommandExecutor, TabCompleter {

    fun points(value: Int, player: Player) {
        val voteNumber = Voting.getVote(player.uniqueId)
        if (voteNumber == value) {
            player.sendMessage("${ChatColor.RED}You have already voted.")
            return
        }
        if (voteNumber == 2 || voteNumber == 3) {
            Voting.delVote(player.uniqueId)
        }
        player.sendMessage("${ChatColor.GREEN}Voted for Points")
        Voting.setVote(player.uniqueId, value)
    }

    fun mechanics(value: Int, player: Player) {
        val voteNumber = Voting.getVote(player.uniqueId)
        if (voteNumber == value) {
            player.sendMessage("${ChatColor.RED}You have already voted.")
            return
        }
        if (voteNumber == 1 || voteNumber == 3) {
            Voting.delVote(player.uniqueId)
        }
        player.sendMessage("${ChatColor.GREEN}Voted for mechanics")
        Voting.setVote(player.uniqueId, value)
    }

    fun none(value: Int, player: Player) {
        val voteNumber = Voting.getVote(player.uniqueId)
        if (voteNumber == value) {
            player.sendMessage("${ChatColor.RED}You have already voted.")
            return
        }
        if (voteNumber == 1 || voteNumber == 2) {
            Voting.delVote(player.uniqueId)
        }
        player.sendMessage("${ChatColor.GREEN}Voted for none")
        Voting.setVote(player.uniqueId, value)
    }

    override fun onCommand(sender: CommandSender, command: Command, commandLabel: String, args: Array<String>): Boolean {
        val player = if (sender is Player) sender else null
        if (player == null) {
            return true
        }

        if (!Voting.voteToggle) {
            player.sendMessage("${ChatColor.RED}Voting is now off please try again in ${String.format("%.2", SiegeWarAPI.getBattleSessionTimeRemaining() / 60000.0)} min")
            return true
        }


        if (args.isEmpty()) {
            player.sendMessage("${ChatColor.RED}Please provide an argument: points, mechanics, or none.")
            return true
        }

        val arg = args[0].lowercase()
        when (arg) {
            "points" -> {
                points(1, player)
            }
            "mechanics" -> {
                mechanics(2, player)
            }
            "none" -> {
                none(3, player)
            }
            "winning" -> {
                val item = Voting.totalVote()
                player.sendMessage("${ChatColor.GREEN}Current winner vote is ${item.title} with ${item.value} votes!")
                return true
            }
            else -> {
                player.sendMessage("${ChatColor.RED}Please provide an argument: points, mechanics, or none.")
                return true
            }
        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<String>): List<String> {
        return listOf("points", "mechanics", "none", "winning")
    }
}
