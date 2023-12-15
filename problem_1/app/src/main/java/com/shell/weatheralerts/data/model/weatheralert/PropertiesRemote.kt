package com.shell.weatheralerts.data.model.weatheralert

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class PropertiesRemote(
    @SerializedName("id")
    val id: String,
    @SerializedName("areaDesc")
    val areaDesc: String,
    @SerializedName("geocode")
    val geocode: GeocodeRemote,
    @SerializedName("affectedZones")
    val affectedZones: List<String>,
    @SerializedName("references")
    val references: List<ReferenceRemote>,
    @SerializedName("sent")
    val sent: LocalDateTime,
    @SerializedName("effective")
    val effective: LocalDateTime,
    @SerializedName("onset")
    val onset: LocalDateTime,
    @SerializedName("expires")
    val expires: LocalDateTime,
    @SerializedName("ends")
    val ends: LocalDateTime,
    @SerializedName("status")
    val status: String,
    @SerializedName("messageType")
    val messageType: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("severity")
    val severity: String,
    @SerializedName("certainty")
    val certainty: String,
    @SerializedName("urgency")
    val urgency: String,
    @SerializedName("event")
    val event: String,
    @SerializedName("sender")
    val sender: String,
    @SerializedName("senderName")
    val senderName: String,
    @SerializedName("headline")
    val headline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("instruction")
    val instruction: String,
    @SerializedName("response")
    val response: String,
    @SerializedName("parameters")
    val parameters: ParametersRemote,
)
