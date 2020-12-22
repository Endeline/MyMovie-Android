package com.endeline.data.responses

import com.google.gson.annotations.SerializedName

data class PersonCredits(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("cast") val cast: List<Person>? = null,
    @SerializedName("crew") val crew: List<Person>? = null
)
