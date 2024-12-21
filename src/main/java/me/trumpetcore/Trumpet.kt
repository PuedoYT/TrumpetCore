package me.trumpetcore

import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import com.github.shynixn.mccoroutine.bukkit.registerSuspendingEvents
import com.github.shynixn.mccoroutine.bukkit.setSuspendingExecutor
import com.github.shynixn.mccoroutine.bukkit.setSuspendingTabCompleter
import com.mongodb.*
import com.mongodb.kotlin.client.coroutine.MongoClient
import me.trumpetcore.commands.ProfileCommand
import me.trumpetcore.commands.RankCommand
import me.trumpetcore.commands.moderation.BanCommand
import me.trumpetcore.listeners.PlayerChatListener
import me.trumpetcore.listeners.PlayerOnlineListener
import me.trumpetcore.listeners.ProfileRegisterListener
import me.trumpetcore.mongodb.MongoDB
import org.bson.BsonInt64
import org.bson.Document
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.time.Duration

class Trumpet : SuspendingJavaPlugin() {

    private val mongoDB: MongoDB = MongoDB()

    override suspend fun onEnableAsync() {

        this.getCommand("profile")!!.setSuspendingExecutor(ProfileCommand())
        this.getCommand("rank")!!.setSuspendingExecutor(RankCommand())
        this.getCommand("rank")!!.setSuspendingTabCompleter(RankCommand())
        this.getCommand("ban")!!.setSuspendingExecutor(BanCommand())
        this.getCommand("ban")!!.setSuspendingTabCompleter(BanCommand())
        server.pluginManager.registerSuspendingEvents(ProfileRegisterListener(), this)
        server.pluginManager.registerSuspendingEvents(PlayerOnlineListener(), this)
        server.pluginManager.registerSuspendingEvents(PlayerChatListener(), this)
        mongoDB.ping()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
