package com.endeline.domain.uimodels

import java.util.*

data class SearchUiModel(
    val page: Int,
    val totalResults: Int,
    val totalPages: Int,
    val results: List<SearchItemUiModel>
) {
    data class SearchItemUiModel(
        val posterPath: String,
        val popularity: Double,
        val voteCount: Int,
        val video: Boolean,
        val mediaType: String,
        val id: Int,
        val adult: Boolean,
        val backdropPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int>,
        val title: String,
        val voteAverage: Double,
        val overview: String,
        val releaseDate: Date,
        val knownForDepartment: String,
        val name: String,
        val knownFor: List<KnownForUiModel>,
        val profilePath: String,
        val gender: Int
    )

    data class KnownForUiModel(
        val posterPath: String,
        val voteCount: Int,
        val video: Boolean,
        val mediaType: String,
        val id: Int,
        val adult: Boolean,
        val backdropPath: String,
        val originalLanguage: String,
        val originalTitle: String,
        val genreIds: List<Int>,
        val title: String,
        val voteAverage: Double,
        val overview: String,
        val releaseDate: Date
    )
}
