package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class Credits(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("cast") val cast: List<Person>? = null,
    @SerializedName("crew") val crew: List<Person>? = null
)
