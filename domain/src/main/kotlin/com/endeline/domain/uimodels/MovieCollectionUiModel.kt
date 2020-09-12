package com.endeline.domain.uimodels

import java.util.*

data class MovieCollectionUiModel(
    val page: Int,
    val results: List<MovieItemUiModel>,
    val dates: DatesUiModel,
    val totalPages: Int,
    val totalResults: Int
) {
    data class DatesUiModel(
        val maximum: String,
        val minimum: String
    )

    data class MovieItemUiModel(
        val posterPath: String,
        val adult: Boolean,
        val overview: String,
        val releaseDate: Date,
        val genreIds: List<Int>,
        val id: Int,
        val originalTitle: String,
        val originalLanguage: String,
        val title: String,
        val backdropPath: String,
        val popularity: Double,
        val voteCount: Int,
        val video: Boolean,
        val voteAverage: Double
    )
}

