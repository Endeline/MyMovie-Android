package com.endeline.domain.extensions


import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.common.types.ProductType
import com.endeline.data.models.Product
import com.endeline.domain.uimodels.ProductUiModel
import java.util.*

fun Product.toUiModel(productType: ProductType) = ProductUiModel(
    firstAirDate = this.firstAirDate ?: EMPTY_TEXT,
    originCountry = this.originCountry ?: emptyList(),
    originalName = this.originalName ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT,
    posterPath = this@toUiModel.posterPath ?: EMPTY_TEXT,
    adult = this@toUiModel.adult ?: false,
    overview = this@toUiModel.overview ?: EMPTY_TEXT,
    releaseDate = this@toUiModel.releaseDate ?: EMPTY_TEXT,
    genreIds = this@toUiModel.genreIds ?: emptyList(),
    id = this@toUiModel.id ?: EMPTY_VALUE,
    originalTitle = this@toUiModel.originalTitle ?: EMPTY_TEXT,
    originalLanguage = this@toUiModel.originalLanguage ?: EMPTY_TEXT,
    title = this@toUiModel.title ?: EMPTY_TEXT,
    backdropPath = this@toUiModel.backdropPath ?: EMPTY_TEXT,
    popularity = this@toUiModel.popularity ?: NO_VALUE,
    voteCount = this@toUiModel.voteCount ?: EMPTY_VALUE,
    video = this@toUiModel.video ?: false,
    voteAverage = this@toUiModel.voteAverage ?: NO_VALUE,
    productType = productType,
    character = this.character ?: EMPTY_TEXT,
    creditId = this.creditId ?: EMPTY_TEXT,
    order = this.order ?: EMPTY_VALUE
)

fun List<Product>.toUiModel(productType: ProductType) = this.map { it.toUiModel(productType) }
