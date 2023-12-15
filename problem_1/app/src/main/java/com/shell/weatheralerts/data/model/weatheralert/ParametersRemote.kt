package com.shell.weatheralerts.data.model.weatheralert

import com.google.gson.annotations.SerializedName

class ParametersRemote(
    @SerializedName("additionalProp1")
    val additionalProp1: List<Any>,
    @SerializedName("additionalProp2")
    val additionalProp2: List<Any>,
    @SerializedName("additionalProp3")
    val additionalProp3: List<Any>,
)
