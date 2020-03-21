package com.endeline.data.pojos

import com.google.gson.annotations.SerializedName

class MovieCollectionPojo (
    @SerializedName("page")
    val page: Int? = null,

    @SerializedName("results")
    val results: List<MovieCollectionItemPojo>? = null,

    @SerializedName("dates")
    val dates: DatesPojo? = null,

    @SerializedName("total_pages")
    val totalPages: Int? = null,

    @SerializedName("total_results")
    val totalResults: Int? = null
)

