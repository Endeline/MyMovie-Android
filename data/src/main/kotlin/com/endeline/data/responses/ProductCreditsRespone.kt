package com.endeline.data.responses

import com.google.gson.annotations.SerializedName

data class ProductCredits(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("cast") val cast: List<Product>? = null,
    @SerializedName("crew") val crew: List<Product>? = null
)
