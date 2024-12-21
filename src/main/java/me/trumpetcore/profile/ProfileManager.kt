package me.trumpetcore.profile

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Projections
import com.mongodb.client.model.Projections.exclude
import com.mongodb.client.model.Projections.include
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.DistinctFlow
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import me.trumpetcore.mongodb.MongoDB
import me.trumpetcore.util.Ranks
import org.bson.Document
import org.bson.conversions.Bson
import org.bukkit.ChatColor
import org.jetbrains.annotations.NotNull
import java.util.*

class ProfileManager {

    private val profile: MongoCollection<PlayerProfile> = MongoDB().database.getCollection<PlayerProfile>("player")

    suspend fun hasProfile(uuid: UUID): Boolean {
        val result = profile.find(Filters.eq("uuid", uuid.toString())).firstOrNull()
        if(result == null) return false
        return true
    }

    suspend fun createProfile(uuid: UUID) {
        val profileID = UUID.randomUUID()
        profile.insertOne(PlayerProfile(profileID.toString(), uuid.toString(), Ranks.Default, true))
    }

    suspend fun getProfileID(uuid: UUID): String {
        profile.find(Filters.eq(PlayerProfile::uuid.name, uuid.toString())).first().let {
            return it.profileID
        }
    }

    suspend fun getRank(uuid: UUID): Ranks {
        profile.find(Filters.eq(PlayerProfile::uuid.name, uuid.toString())).first().let {
            return it.rank
        }
    }

    suspend fun setRank(uuid: UUID, rank: Ranks) {
        profile.updateOne(Filters.eq(PlayerProfile::uuid.name, uuid.toString()), Updates.set(PlayerProfile::rank.name, rank))
    }

    suspend fun isOnline(uuid: UUID): Boolean {
        profile.find(Filters.eq(PlayerProfile::uuid.name, uuid.toString())).first().let {
            return it.online
        }
    }

    suspend fun setOnline(uuid: UUID, online: Boolean) {
        profile.updateOne(Filters.eq(PlayerProfile::uuid.name, uuid.toString()), Updates.set(PlayerProfile::online.name, online))
    }
}