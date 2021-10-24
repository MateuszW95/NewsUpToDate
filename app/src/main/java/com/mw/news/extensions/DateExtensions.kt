package com.mw.news.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toLocalDate(
    pattern: String,
    timeZone: TimeZone = TimeZone.getTimeZone("UTC")
): Date? {
    return try {
        SimpleDateFormat(pattern, Locale.US).apply {
            this.timeZone = timeZone
        }.parse(this)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Date.displayDateWith(
    pattern: String,
    locale: Locale = Locale.US,
    timezone: TimeZone = TimeZone.getDefault()
): String {
    return SimpleDateFormat(pattern, locale).apply { timeZone = timezone }.format(this)
}