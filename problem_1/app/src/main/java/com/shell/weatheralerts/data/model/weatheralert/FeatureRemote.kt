package com.shell.weatheralerts.data.model.weatheralert

import com.google.gson.annotations.SerializedName

class FeatureRemote(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("properties")
    val properties: PropertiesRemote,
)
