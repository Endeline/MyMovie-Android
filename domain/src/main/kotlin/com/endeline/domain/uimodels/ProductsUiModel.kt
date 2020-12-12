package com.endeline.domain.uimodels

import com.endeline.common.ProductType
import java.util.*

data class ProductsUiModel(
    val page: Int,
    val results: List<ProductUiModel>,
    val dates: DatesUiModel,
    val totalPages: Int,
    val totalResults: Int
) {
    data class DatesUiModel(
        val maximum: String,
        val minimum: String
    )

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
        val voteAverage: Double
    )
}

