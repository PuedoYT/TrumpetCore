package me.trumpetcore.commands

import com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor
import me.trumpetcore.profile.ProfileManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import java.util.*

class ProfileCommand : SuspendingCommandExecutor {
    override suspend fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        sender.sendMessage("${ChatColor.GREEN}Querying profile for player ${args[0]} ...")

        val profile = ProfileManager()
        val targetUUID: UUID = Bukkit.getPlayer(args[0]).uniqueId
        if(profile.hasProfile(targetUUID)) {

            sender.sendMessage("${ChatColor.GRAY}${ChatColor.STRIKETHROUGH}----------------------------------------------------")
            sender.sendMessage("${ChatColor.YELLOW}Profile ID: ${ChatColor.WHITE}${profile.getProfileID(targetUUID)}")
            sender.sendMessage("${ChatColor.YELLOW}Player UUID: ${ChatColor.WHITE}${targetUUID}")
            sender.sendMessage("${ChatColor.YELLOW}Username: ${ChatColor.WHITE}${Bukkit.getPlayer(targetUUID).name}")
            sender.sendMessage("${ChatColor.YELLOW}Rank: ${ChatColor.WHITE}${profile.getRank(targetUUID)}")
            sender.sendMessage("${ChatColor.YELLOW}Online: ${ChatColor.WHITE}${profile.isOnline(targetUUID)}")
            sender.sendMessage("${ChatColor.GRAY}${ChatColor.STRIKETHROUGH}----------------------------------------------------")
            return true
        } else {
            sender.sendMessage("${ChatColor.RED}Could not find player!")
        }

        return false
    }
}