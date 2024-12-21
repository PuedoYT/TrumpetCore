package me.trumpetcore.commands.moderation

import com.github.shynixn.mccoroutine.bukkit.SuspendingCommandExecutor
import com.github.shynixn.mccoroutine.bukkit.SuspendingTabCompleter
import me.trumpetcore.profile.ProfileManager
import me.trumpetcore.profile.PunishmentManager
import me.trumpetcore.util.Punishments
import me.trumpetcore.util.PunishmentsReasonsDurations
import me.trumpetcore.util.Ranks
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class BanCommand : SuspendingCommandExecutor, SuspendingTabCompleter {
    override suspend fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {

        val profile = ProfileManager()
        val punishmentManager: PunishmentManager = PunishmentManager()

        val targetUUID: UUID = Bukkit.getPlayer(args[0]).uniqueId
        val reason = args[1]
        var staff = ""
        if(sender is Player) {
            staff = sender.uniqueId.toString()
        } else {
            staff = "00000000-0000-0000-0000-000000000000"
        }

        if(profile.hasProfile(targetUUID)) {
            punishmentManager.createPunishment(targetUUID, UUID.fromString(staff), PunishmentsReasonsDurations.getFromStr(args[1]))
            sender.sendMessage("${ChatColor.GREEN}Punished player for ${ChatColor.WHITE}${reason}")
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
            val reasons: ArrayList<String> = ArrayList<String>()
            for(s in PunishmentsReasonsDurations.entries) {
                if(s.type.equals(Punishments.BAN)) {
                    reasons.add(s.pname)
                }
            }
            return reasons
        }
        return null
    }
}