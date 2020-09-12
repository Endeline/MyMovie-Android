package com.endeline.domain.uimodels

import java.util.*

data class MovieDetailsUiModel(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: BelongsToCollectionUiModel,
    val budget: Int,
    val genres: List<GenresUiModel>,
    val homepage: String,
    val id: Int,
    val imdbId: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompaniesUiModel>,
    val productionCountries: List<ProductionCountriesUiModel>,
    val releaseDate: Date,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguagesUiModel>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    data class BelongsToCollectionUiModel(
        val id: Int,
        val name: String,
        val posterPath: String,
        val backdropPath: String
    )

    data class GenresUiModel(
        val id: Int,
        val name: String
    )

    data class ProductionCompaniesUiModel(
        val id: Int,
        val logoPath: String,
        val name: String,
        val originCountry: String
    )

    data class ProductionCountriesUiModel(
        val iso_3166_1: String,
        val name: String
    )

    data class SpokenLanguagesUiModel(
        val iso_639_1: String,
        val name: String
    )
}
