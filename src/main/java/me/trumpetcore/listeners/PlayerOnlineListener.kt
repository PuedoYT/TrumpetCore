package me.trumpetcore.listeners

import me.trumpetcore.profile.ProfileManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerOnlineListener : Listener {

    private val profile: ProfileManager = ProfileManager()

    @EventHandler
    suspend fun onJoin(event: PlayerJoinEvent) {
        profile.setOnline(event.player.uniqueId, true)
    }

    @EventHandler
    suspend fun onLeave(event: PlayerQuitEvent) {
        profile.setOnline(event.player.uniqueId, false)
    }
}