package me.trumpetcore.util

import org.bukkit.ChatColor

enum class Ranks(val rankName: String, val color: ChatColor, val displayName: String, val prefix: String, val weight: Int, val grantable: Boolean) {

    Owner("Owner", ChatColor.RED, "${ChatColor.RED}Owner", "${ChatColor.RED}[Owner] ", 800, false),
    Admin("Admin", ChatColor.RED, "${ChatColor.RED}Admin", "${ChatColor.RED}[Admin] ", 700, true),
    Mod("Mod", ChatColor.DARK_GREEN, "${ChatColor.DARK_GREEN}Mod", "${ChatColor.DARK_GREEN}[Mod] ", 600, true),
    Helper("Helper", ChatColor.BLUE, "${ChatColor.BLUE}Helper", "${ChatColor.BLUE}[Helper] ", 500, true),
    Default("Default", ChatColor.GRAY, "${ChatColor.GRAY}", "${ChatColor.GRAY} ", 1, true);

    companion object {
        fun getRankFromString(rank: String): Ranks {
            for(s in entries) {
                if(s.rankName == rank) {
                    return s
                }
            }
            return Default
        }
    }

}