package com.shell.weatheralerts.data.model.weatheralert

import com.google.gson.annotations.SerializedName

class GeocodeRemote(
    @SerializedName("UGC")
    val ugc: List<String>,
    @SerializedName("SAME")
    val same: List<String>,
)