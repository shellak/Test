package com.shell.weatheralerts.data.model.weatheralert

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class WeatherAlertRemote(
    @SerializedName("type")
    val type: String,
    @SerializedName("features")
    val features: List<FeatureRemote>,
    @SerializedName("title")
    val title: String,
    @SerializedName("updated")
    val updated: LocalDateTime,
    @SerializedName("pagination")
    val pagination: PaginationRemote,
)
