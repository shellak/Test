package com.shell.weatheralerts.domain.repository

import android.graphics.Bitmap
import com.shell.weatheralerts.domain.model.WeatherAlert

interface WeatherAlertRepository {

    suspend fun getWeatherActiveAlerts(
        statuses: List<String>,
        messageTypes: List<String>,
    ): List<WeatherAlert>

    suspend fun getWeatherActiveAlertImage(urlString: String): Bitmap?
}
