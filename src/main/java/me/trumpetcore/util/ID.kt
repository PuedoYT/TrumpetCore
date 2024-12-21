package me.trumpetcore.util

import java.util.*
import kotlin.streams.asSequence

class ID {
    fun gen(): String {
        val source = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        return Random().ints(6, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString("")
    }
}