package com.endeline.data.pojos

import com.google.gson.annotations.SerializedName

data class GenresPojo (
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("name")
    val name: String? = null
)

