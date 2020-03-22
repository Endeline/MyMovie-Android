package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class Dates (
    @SerializedName("maximum")
    val maximum: String? = null,

    @SerializedName("minimum")
    val minimum: String? = null
)
