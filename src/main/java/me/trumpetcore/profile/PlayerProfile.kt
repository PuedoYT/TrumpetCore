package me.trumpetcore.profile

import me.trumpetcore.util.Ranks
import java.util.UUID

data class PlayerProfile(
    val profileID: String,
    val uuid: String,
    val rank: Ranks,
    val online: Boolean
)
