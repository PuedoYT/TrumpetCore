package me.trumpetcore.commands

import com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor
import com.github.shynixn.mccoroutine.bukkit.SuspendingTabCompleter
import me.trumpetcore.profile.ProfileManager
import me.trumpetcore.util.Ranks
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import java.util.*
import kotlin.collections.ArrayList

class RankCommand : SuspendingCommandExecutor, SuspendingTabCompleter {
    override suspend fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        sender.sendMessage("${ChatColor.GREEN}Querying profile for player ${args[0]} ...")

        val profile = ProfileManager()

        val targetUUID: UUID = Bukkit.getPlayer(args[0]).uniqueId
        val rank: Ranks = Ranks.getRankFromString(args[1])
        if(profile.hasProfile(targetUUID)) {
            profile.setRank(targetUUID, rank)
            sender.sendMessage("${ChatColor.GREEN}Successfully changed ${Bukkit.getPlayer(targetUUID).name}'s rank to ${rank.displayName}...")
            Bukkit.getPlayer(targetUUID).sendMessage("${ChatColor.GREEN}Your rank has been updated to ${rank.displayName}")
            return true
        } else {
            sender.sendMessage("${ChatColor.RED}Could not find player!")
        }

        return false
    }

    override suspend fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): ArrayList<String>? {
        if(args.size == 2) {
            val ranks: ArrayList<String> = ArrayList<String>()
            for(s in Ranks.entries) {
                if(s.grantable) {
                    ranks.add(s.rankName)
                }
            }
            return ranks
        }
        return null
    }
}