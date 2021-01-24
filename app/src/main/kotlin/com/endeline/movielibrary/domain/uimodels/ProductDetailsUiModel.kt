package com.endeline.movielibrary.domain.uimodels

import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import java.util.*

data class ProductDetailsUiModel(
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
    val voteCount: Int,
    val createdBy: List<PersonUiModel>,
    val episodeRunTime: List<Int>,
    val inProduction: Boolean,
    val firstAirDate: String,
    val lastAirDate: String,
    val numberOfSeasons: Int,
    val numberOfEpisodes: Int,
    val type: String,
    val seasons: List<SeasonUiModel>,
    val nextEpisodeToAir: EpisodeUiModel,
    val lastEpisodeToAir: EpisodeUiModel,
    val name: String
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

    data class SeasonUiModel(
        val airDate: String,
        val episodeCount: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val posterPath: String,
        val seasonNumber: Int
    )

    data class EpisodeUiModel(
        val airDate: String,
        val episodeNumber: Int,
        val id: Int,
        val name: String,
        val overview: String,
        val productionCode: String,
        val seasonNumber: Int,
        val stillPath: String,
        val voteAverage: Double,
        val voteCount: Int
    ) {
        companion object {
            val EMPTY = EpisodeUiModel(
                airDate = EMPTY_TEXT,
                episodeNumber = EMPTY_VALUE,
                id = EMPTY_VALUE,
                name = EMPTY_TEXT,
                overview = EMPTY_TEXT,
                productionCode = EMPTY_TEXT,
                seasonNumber = EMPTY_VALUE,
                stillPath = EMPTY_TEXT,
                voteAverage = NO_VALUE,
                voteCount = EMPTY_VALUE
            )
        }
    }
}
