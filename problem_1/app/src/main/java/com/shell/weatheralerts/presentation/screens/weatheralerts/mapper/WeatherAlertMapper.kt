package com.shell.weatheralerts.presentation.screens.weatheralerts.mapper

import android.graphics.Bitmap
import com.shell.weatheralerts.domain.model.WeatherAlert
import com.shell.weatheralerts.presentation.screens.weatheralerts.model.WeatherAlertUi
import com.shell.weatheralerts.utils.date.DateFormats
import com.shell.weatheralerts.utils.date.DateFormats.getFormattedDate
import javax.inject.Inject

class WeatherAlertUiMapper @Inject constructor() {

    fun map(weatherAlert: WeatherAlert, image: Bitmap?) =
        with(weatherAlert) {
            WeatherAlertUi(
                id = id,
                event = event,
                effective = effective.getFormattedDate(),
                ends = ends.getFormattedDate(),
                duration = DateFormats.getDuration(effective, ends),
                senderName = senderName,
                image = image,
            )
        }
}
