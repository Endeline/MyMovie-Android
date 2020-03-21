package com.endeline.data.pojos

import com.google.gson.annotations.SerializedName

data class DatesPojo (
    @SerializedName("maximum")
    val maximum: String? = null,

    @SerializedName("minimum")
    val minimum: String? = null
)
