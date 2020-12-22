package com.endeline.domain.uimodels

import com.endeline.common.types.ProductType

data class ProductUiModel(
    val productType: ProductType,
    val firstAirDate: String,
    val originCountry: List<String>,
    val originalName: String,
    val name: String,
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalTitle: String,
    val originalLanguage: String,
    val title: String,
    val backdropPath: String,
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val voteAverage: Double,
    val character: String,
    val creditId: String,
    val order: Int
)
