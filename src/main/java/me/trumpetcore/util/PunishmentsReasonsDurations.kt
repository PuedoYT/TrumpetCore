package me.trumpetcore.util

import com.mongodb.client.model.Filters
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import me.trumpetcore.profile.PlayerProfile
import me.trumpetcore.profile.PunishmentManager
import java.time.Duration
import java.util.*

enum class PunishmentsReasonsDurations(val pname: String, val reason: String, val type: Punishments, val duration: LongArray) {

    CHEATING("Cheating", "Blacklisted modifications (Cheating)",
        Punishments.BAN,
        longArrayOf(
            TimeUtil().daysToMilis(30),
            TimeUtil().daysToMilis(60),
            TimeUtil().daysToMilis(90),
            TimeUtil().daysToMilis(180)
        )),
    NONE_FOUND("None", "No reason found", Punishments.BAN, longArrayOf(
        Duration.ZERO.toMillis()
    ));

    companion object {
        suspend fun getFromStr(s: String): PunishmentsReasonsDurations {
            for(i in entries) {
                if(i.pname == s) {
                    return i
                }
            }
            return NONE_FOUND
        }
    }

}