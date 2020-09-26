package com.endeline.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ProductDetails(
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("genres") val genres: List<Genres>?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("release_date") val releaseDate: Date?,
    @SerializedName("revenue") val revenue: Int?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("status") val status: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguages>?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompanies>?,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountries>?,
    @SerializedName("belongs_to_collection") val belongsToCollection: BelongsToCollection?
) {
    data class BelongsToCollection(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?,
        @SerializedName("poster_path") val posterPath: String?,
        @SerializedName("backdrop_path") val backdropPath: String?
    ) {
        companion object {
            val EMPTY = BelongsToCollection(-1, "", "", "")
        }
    }

    data class Genres(
        @SerializedName("id") val id: Int?,
        @SerializedName("name") val name: String?
    )

    data class SpokenLanguages(
        @SerializedName("iso_639_1") val iso_639_1: String?,
        @SerializedName("name") val name: String?
    )

    data class ProductionCompanies(
        @SerializedName("id") val id: Int?,
        @SerializedName("logo_path") val logoPath: String?,
        @SerializedName("name") val name: String?,
        @SerializedName("origin_country") val originCountry: String?
    )

    data class ProductionCountries(
        @SerializedName("iso_3166_1") val iso_3166_1: String?,
        @SerializedName("name") val name: String?
    )
}
