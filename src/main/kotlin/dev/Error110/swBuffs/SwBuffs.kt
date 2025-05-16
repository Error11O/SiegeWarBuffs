package dev.Error110.swBuffs

import dev.Error110.swBuffs.Commands.buffVote
import dev.Error110.swBuffs.Listener.inZoneListener
import dev.Error110.swBuffs.Listener.siegesTimes
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin

class SwBuffs : JavaPlugin() {

    private lateinit var plugin: SwBuffs

    override fun onEnable() {
        val logger = this.logger
        val pluginManager = this.server.pluginManager

        pluginManager.registerEvents(inZoneListener(this, this.logger), this)
        pluginManager.registerEvents(siegesTimes(this, this.logger), this)
        getCommand("buffvote")?.setExecutor(buffVote())
        this.getCommand("buffvote")?.setTabCompleter(this.getCommand("buffvote")?.getExecutor() as TabCompleter)
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
