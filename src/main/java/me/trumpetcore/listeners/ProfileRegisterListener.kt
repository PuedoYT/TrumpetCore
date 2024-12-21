package me.trumpetcore.listeners

import me.trumpetcore.profile.ProfileManager
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerLoginEvent

class ProfileRegisterListener : Listener {

    private val profile: ProfileManager = ProfileManager()

    @EventHandler
    suspend fun onLogin(event: PlayerLoginEvent) {
        if(!profile.hasProfile(event.player.uniqueId)) {
            profile.createProfile(event.player.uniqueId)
            event.player.sendMessage("${ChatColor.RED}[DEBUG] ${ChatColor.GREEN}New profile created in MongoDB!")
            println("Created new profile for player with UUID ${event.player.uniqueId}")
        }
    }
}