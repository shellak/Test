package com.shell.weatheralerts.data.repository

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.shell.weatheralerts.data.mapper.WeatherAlertMapper
import com.shell.weatheralerts.data.service.WeatherAlertService
import com.shell.weatheralerts.domain.repository.WeatherAlertRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL
import javax.inject.Inject

class WeatherAlertRepositoryImpl @Inject constructor(
    private val service: WeatherAlertService,
    private val weatherAlertMapper: WeatherAlertMapper
) : WeatherAlertRepository {

    override suspend fun getWeatherActiveAlerts(
        statuses: List<String>,
        messageTypes: List<String>,
    ) = withContext(Dispatchers.IO) {
        weatherAlertMapper.map(
            service.getActiveAlerts(
                statuses = statuses,
                messageTypes = messageTypes,
            )
        )
    }

    override suspend fun getWeatherActiveAlertImage(urlString: String): Bitmap? =
        withContext(Dispatchers.IO) {
            URL(urlString).openConnection().getInputStream().use { inputStream ->
                BitmapFactory.decodeStream(inputStream)
            }
        }

}
