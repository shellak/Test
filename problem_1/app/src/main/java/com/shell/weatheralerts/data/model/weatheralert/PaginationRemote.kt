package com.shell.weatheralerts.data.model.weatheralert

import com.google.gson.annotations.SerializedName

class PaginationRemote(
    @SerializedName("next")
    val next: String,
)
