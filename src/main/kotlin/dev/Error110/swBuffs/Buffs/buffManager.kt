package dev.Error110.swBuffs.Buffs

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin

class buffManager {

    fun cataToBuff(wonVote: String): String {
        var buffs = listOf<String>()

        when (wonVote) {
            "points" -> {
                buffs = listOf<String>("Zombies", "assassins")
            }
            "mechanics" -> {
                buffs = listOf<String>("rainingCarts", "nethMode")
            }
            "none" -> {

            }
        }
        if (buffs.isEmpty()) {
            return "none"
        }
        return buffs.shuffled().first().toString()
    }

    //thing doing the buff
    fun buffAction(buffName: String, player: Player, plugin: Plugin) {
        when (buffName) {
            "Zombies" -> zombieBuff().waves(player, plugin)
            "assassins" -> assassins()
            "rainingCarts" -> rainingCarts().cartPlayer(player, plugin)
            "nethMode" -> nethMode()
            "none" -> return
        }
    }
}