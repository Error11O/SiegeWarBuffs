package dev.Error110.swBuffs.Buffs

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Effect
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.Plugin
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.scheduler.BukkitRunnable

class zombieBuff() {
    var rounds = 0
    var waves = 1
    fun waves(player: Player, plugin: Plugin) {
        rounds++
        if (rounds % 45 == 0) {
            waves++
            Bukkit.broadcastMessage("${ChatColor.GREEN}WAVE $waves")
        }
        hoard(player, plugin)
    }

    fun hoard(player: Player, plugin: Plugin) {
        val randomNumber = (1..100).random()
        if (randomNumber < 80) {
            var xOffset = (Math.random() * 6) - 2
            var zOffset = (Math.random() * 6) - 2

            val world = Bukkit.getWorld("world")
            val hordLocation = Location(world, player.location.x + xOffset, player.location.y, player.location.z + zOffset)
            object : BukkitRunnable() {
                override fun run() {
                    spawnZombie(hordLocation, world!!)
                }
            }.runTask(plugin)
        }
    }

    fun spawnZombie(location: Location, world: World) {
        val zombie = world.spawnEntity(location, EntityType.ZOMBIE) as Zombie

        val sword = ItemStack(Material.DIAMOND_SWORD)
        val armorPieces = listOf(
            ItemStack(Material.DIAMOND_HELMET),
            ItemStack(Material.DIAMOND_CHESTPLATE),
            ItemStack(Material.DIAMOND_LEGGINGS),
            ItemStack(Material.DIAMOND_BOOTS)
        )
        sword.addEnchantment(Enchantment.SHARPNESS, 1 + waves)

        val randomNumber = (1..10).random()
        if (randomNumber == 5) {
            sword.addEnchantment(Enchantment.KNOCKBACK, 2)
        }

        for (armor in armorPieces) {
            armor.addEnchantment(Enchantment.PROTECTION, waves)
            armor.addEnchantment(Enchantment.VANISHING_CURSE, 1)
        }

        zombie.equipment?.helmet = armorPieces[0]
        zombie.equipment?.chestplate = armorPieces[1]
        zombie.equipment?.leggings = armorPieces[2]
        zombie.equipment?.boots = armorPieces[3]
        zombie.equipment?.setItemInMainHand(sword)
        val effect = PotionEffect(PotionEffectType.SPEED, PotionEffect.INFINITE_DURATION, 2)
        zombie.addPotionEffect(effect)
    }
}