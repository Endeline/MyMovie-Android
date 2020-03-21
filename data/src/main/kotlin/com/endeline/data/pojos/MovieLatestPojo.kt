package com.endeline.data.pojos

import com.google.gson.annotations.SerializedName

class MovieLatestPojo (
    @SerializedName("adult")
    val adult: Boolean? = null,

    @SerializedName("backdrop_path")
    val backdropPath: String? = null,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: String? = null,

    @SerializedName("budget")
    val budget: Int? = null,

    @SerializedName("genres")
    val genres: List<GenresPojo>? = null,

    @SerializedName("homepage")
    val homepage: String? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("imdb_id")
    val imdbId: String? = null,

    @SerializedName("original_language")
    val originalLanguage: String? = null,

    @SerializedName("original_title")
    val originalTitle: String? = null,

    @SerializedName("overview")
    val overview: String? = null,

    @SerializedName("popularity")
    val popularity: Int? = null,

    @SerializedName("poster_path")
    val posterPath: String? = null,

    @SerializedName("production_companies")
    val productionCompanies: List<String>? = null,

    //todo find this in doc
//    @SerializedName("production_countries")
//    val productionCountries: List<String>? = null,

    @SerializedName("release_date")
    val releaseDate: String? = null,

    @SerializedName("revenue")
    val revenue: Int? = null,

    @SerializedName("runtime")
    val runtime: Int? = null,

    //todo find this object in doc
//    @SerializedName("spoken_languages")
//    val spokenLanguages: List<String>? = null,

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("tagline")
    val tagline: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("video")
    val video: Boolean? = null,

    @SerializedName("vote_average")
    val voteAverage: Int? = null,

    @SerializedName("vote_count")
    val voteCount: Int? = null
)
