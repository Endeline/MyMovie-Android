package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class MovieCollection (
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: List<MovieCollectionItem>? = null,

    @SerializedName("dates")
    val dates: Dates? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null
)

