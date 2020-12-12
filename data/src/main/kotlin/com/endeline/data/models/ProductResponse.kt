package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("first_air_date") val firstAirDate: String?,
    @SerializedName("origin_country") val originCountry: List<String>?,
    @SerializedName("original_name") val originalName: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genre_ids") val genreIds: List<Int>?,
    @SerializedName("id") val id: Int?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("character") val character: String?,
    @SerializedName("credit_id") val creditId: String?,
    @SerializedName("order") val order: Int?
)