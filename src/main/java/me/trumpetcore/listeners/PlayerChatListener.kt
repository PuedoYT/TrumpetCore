package me.trumpetcore.listeners

import me.trumpetcore.profile.ProfileManager
import me.trumpetcore.util.Ranks
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class PlayerChatListener : Listener {

    private val profile: ProfileManager = ProfileManager()

    @EventHandler
    suspend fun onChat(event: AsyncPlayerChatEvent) {
        event.setFormat("${Ranks.getRankFromString(profile.getRank(event.player.uniqueId).name).prefix}${event.player.name}${ChatColor.GRAY}: ${ChatColor.WHITE}${event.message}")
    }
}