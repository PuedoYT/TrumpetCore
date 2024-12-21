package me.trumpetcore.profile

import com.mongodb.client.model.Filters
import com.mongodb.internal.connection.Time
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import me.trumpetcore.mongodb.MongoDB
import me.trumpetcore.util.ID
import me.trumpetcore.util.PunishmentScheme
import me.trumpetcore.util.PunishmentsReasonsDurations
import java.time.Duration
import java.time.Instant
import java.util.*

class PunishmentManager {

    val punishments: MongoCollection<PunishmentScheme> = MongoDB().database.getCollection<PunishmentScheme>("punishments")

    suspend fun hasActivePunishment(uuid: UUID): Boolean {
        punishments.find(Filters.eq(PlayerProfile::uuid.name, uuid.toString())).first().let {
            return it.active
        }
    }

    suspend fun createPunishment(uuid: UUID, staff: UUID, reason: PunishmentsReasonsDurations) {
        punishments.insertOne(PunishmentScheme(ID().gen(), uuid.toString(), staff.toString(), Instant.now().toEpochMilli(), -1, Duration.ofDays(getNextPunishment(uuid, reason)).toMillis(), reason.reason, true))
    }

    private suspend fun getNextPunishment(uuid: UUID, reason: PunishmentsReasonsDurations): Long {
        //val a = Filters.and(Filters.eq("uuid", uuid.toString()), Filters.eq("active", true), Filters.eq("reason", reason.reason))
        //val n = punishments.find(a).count()

        /*if(PunishmentsReasonsDurations.getFromStr(reason.reason).duration.size < n) {
            return PunishmentsReasonsDurations.getFromStr(reason.reason).duration[PunishmentsReasonsDurations.getFromStr(reason.reason).duration.size - 1]
        }*/

        return PunishmentsReasonsDurations.getFromStr(reason.reason).duration[1]
    }


}