package com.shell.weatheralerts.data.model.weatheralert

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

class ReferenceRemote(
    @SerializedName("@id")
    val id: String,
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("sender")
    val sender: String,
    @SerializedName("sent")
    val sent: LocalDateTime,
)
