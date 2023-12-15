package com.shell.weatheralerts.domain.model

import java.time.LocalDateTime

data class WeatherAlert(
    val id: String,
    val event: String,
    val effective: LocalDateTime?,
    val ends: LocalDateTime?,
    val senderName: String,
)
