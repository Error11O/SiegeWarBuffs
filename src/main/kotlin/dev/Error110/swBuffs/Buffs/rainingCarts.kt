package dev.Error110.swBuffs.Buffs

import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable

class rainingCarts {

    fun cartPlayer(player: Player, plugin: Plugin) {
        val randomNumber = (1..100).random()
        if (randomNumber < 50) {
            var xOffset = (Math.random() * 6) - 2
            var zOffset = (Math.random() * 6) - 2
            var y = 60.00

            val world = Bukkit.getWorld("world")
            val cartLocation = Location(world, player.location.x + xOffset, player.location.y + y, player.location.z + zOffset)
            object : BukkitRunnable() {
                override fun run() {
                    world!!.spawnEntity(cartLocation, EntityType.TNT_MINECART)
                }
            }.runTask(plugin)
        }
    }
}