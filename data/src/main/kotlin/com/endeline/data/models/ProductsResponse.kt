package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<Product>?,
    @SerializedName("dates") val dates: Dates?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
) {
    data class Dates(
        @SerializedName("maximum") val maximum: String?,
        @SerializedName("minimum") val minimum: String?
    ) {
        companion object {
            val EMPTY = Dates("", "")
        }
    }
}

