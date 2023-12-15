package com.shell.weatheralerts.data.mapper

import com.shell.weatheralerts.data.model.weatheralert.WeatherAlertRemote
import com.shell.weatheralerts.domain.model.WeatherAlert
import javax.inject.Inject

class WeatherAlertMapper @Inject constructor() {

    fun map(weatherAlert: WeatherAlertRemote) =
        weatherAlert.features.map { feature ->
            with(feature.properties) {
                WeatherAlert(
                    id = id,
                    event = event,
                    effective = effective,
                    ends = ends,
                    senderName = senderName
                )
            }
        }
}
