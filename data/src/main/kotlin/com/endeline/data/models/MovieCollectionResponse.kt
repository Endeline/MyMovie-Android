package com.endeline.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieCollection(
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<MovieItem>?,
    @SerializedName("dates") val dates: Dates?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
) {
    data class MovieItem(
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("adult") val adult: Boolean?,
        @SerializedName("overview") val overview: String?,
        @SerializedName("release_date") val releaseDate: Date?,
        @SerializedName("genre_ids") val genreIds: List<Int>?,
        @SerializedName("id") val id: Int?,
        @SerializedName("original_title") val originalTitle: String?,
        @SerializedName("original_language") val originalLanguage: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("backdrop_path") val backdropPath: String?,
        @SerializedName("popularity") val popularity: Double?,
        @SerializedName("vote_count") val voteCount: Int?,
        @SerializedName("video") val video: Boolean?,
        @SerializedName("vote_average") val voteAverage: Double?
    )

    data class Dates(
        @SerializedName("maximum") val maximum: String?,
        @SerializedName("minimum") val minimum: String?
    ) {
        companion object {
            val EMPTY = Dates("", "")
        }
    }
}
