package me.trumpetcore.util

import java.time.Duration

data class PunishmentScheme(
    val id: String, //Use ID.gen()
    val playerUUID: String,
    val staffUUID: String,
    val addedAt: Long, //timestamp
    val removedAt: Long, //timestamp
    val duration: Long,
    val reason: String,
    val active: Boolean
)