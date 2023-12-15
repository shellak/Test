package com.shell.weatheralerts.data.service

import com.shell.weatheralerts.data.model.weatheralert.WeatherAlertRemote
import retrofit2.http.GET
import retrofit2.http.Query

const val STATUS_ACTUAL = "actual"
const val MESSAGE_TYPE_ALERT = "alert"

interface WeatherAlertService {

    @GET("/alerts/active")
    suspend fun getActiveAlerts(
        @Query("status") statuses: List<String>,
        @Query("message_type") messageTypes: List<String>,
    ): WeatherAlertRemote
}
