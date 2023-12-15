package com.shell.weatheralerts.utils.date

import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateFormats {
    const val DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX"

    private const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm"
    private const val DURATION_PATTERN = "%02d:%02d"
    private const val NOT_AVAILABLE = "N/A"

    fun LocalDateTime?.getFormattedDate(): String {
        return this?.format(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)).orEmpty().ifEmpty { NOT_AVAILABLE }
    }

    fun getDuration(startDate: LocalDateTime?, endDate: LocalDateTime?) =
        when {
            startDate == null || endDate == null -> NOT_AVAILABLE
            else -> Duration.between(startDate, endDate).format(DURATION_PATTERN)
        }

    private fun Duration.format(pattern: String): String {
        val hours = toHours()
        val minutes = (toMinutes() % 60)
        return pattern.format(hours, minutes)
    }
}
