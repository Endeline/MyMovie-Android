package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class SearchItem(
    @SerializedName("poster_path")
    val posterPath: String?,

    @SerializedName("popularity")
    val popularity: Double?,

    @SerializedName("vote_count")
    val voteCount: Int?,

    @SerializedName("video")
    val video: Boolean?,

    @SerializedName("media_type")
    val mediaType: String?,

    @SerializedName("id")
    val id: Int?,

    @SerializedName("adult")
    val adult: Boolean?,

    @SerializedName("backdrop_path")
    val backdropPath: String?,

    @SerializedName("original_language")
    val originalLanguage: String?,

    @SerializedName("original_title")
    val originalTitle: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("vote_average")
    val voteAverage: Double?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("known_for_department")
    val knownForDepartment: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("known_for")
    val knownFor: List<KnownFor>?,

    @SerializedName("profile_path")
    val profilePath: String?,

    @SerializedName("gender")
    val gender: Int?
)
