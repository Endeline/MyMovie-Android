package com.endeline.domain.extensions


import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.common.types.ProductType
import com.endeline.data.responses.Product
import com.endeline.domain.uimodels.ProductUiModel

fun Product.toUiModel(productType: ProductType) = ProductUiModel(
    firstAirDate = this.firstAirDate ?: EMPTY_TEXT,
    originCountry = this.originCountry ?: emptyList(),
    originalName = this.originalName ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT,
    posterPath = this.posterPath ?: EMPTY_TEXT,
    adult = this.adult ?: false,
    overview = this.overview ?: EMPTY_TEXT,
    releaseDate = this.releaseDate ?: EMPTY_TEXT,
    genreIds = this.genreIds ?: emptyList(),
    id = this.id ?: EMPTY_VALUE,
    originalTitle = this.originalTitle ?: EMPTY_TEXT,
    originalLanguage = this.originalLanguage ?: EMPTY_TEXT,
    title = this.title ?: EMPTY_TEXT,
    backdropPath = this.backdropPath ?: EMPTY_TEXT,
    popularity = this.popularity ?: NO_VALUE,
    voteCount = this.voteCount ?: EMPTY_VALUE,
    video = this.video ?: false,
    voteAverage = this.voteAverage ?: NO_VALUE,
    productType = productType,
    character = this.character ?: EMPTY_TEXT,
    creditId = this.creditId ?: EMPTY_TEXT,
    order = this.order ?: EMPTY_VALUE
)

fun List<Product>.toUiModel(productType: ProductType) = this.map { it.toUiModel(productType) }
