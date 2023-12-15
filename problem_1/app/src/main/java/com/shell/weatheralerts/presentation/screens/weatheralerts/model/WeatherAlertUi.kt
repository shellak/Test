package com.shell.weatheralerts.presentation.screens.weatheralerts.model

import android.graphics.Bitmap

data class WeatherAlertUi(
    val id: String,
    val event: String,
    val effective: String,
    val ends: String,
    val duration: String,
    val senderName: String,
    val image: Bitmap?,
)
