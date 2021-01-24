package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.data.responses.Product

fun Product.toUiModel(productType: ProductType) = ProductUiModel(
    firstAirDate = firstAirDate ?: EMPTY_TEXT,
    originCountry = originCountry ?: emptyList(),
    originalName = originalName ?: EMPTY_TEXT,
    name = name ?: EMPTY_TEXT,
    posterPath = posterPath ?: EMPTY_TEXT,
    adult = adult ?: false,
    overview = overview ?: EMPTY_TEXT,
    releaseDate = releaseDate ?: EMPTY_TEXT,
    genreIds = genreIds ?: emptyList(),
    id = id ?: EMPTY_VALUE,
    originalTitle = originalTitle ?: EMPTY_TEXT,
    originalLanguage = originalLanguage ?: EMPTY_TEXT,
    title = title ?: EMPTY_TEXT,
    backdropPath = backdropPath ?: EMPTY_TEXT,
    popularity = popularity ?: NO_VALUE,
    voteCount = voteCount ?: EMPTY_VALUE,
    video = video ?: false,
    voteAverage = voteAverage ?: NO_VALUE,
    productType = productType,
    character = character ?: EMPTY_TEXT,
    creditId = creditId ?: EMPTY_TEXT,
    order = order ?: EMPTY_VALUE
)

fun List<Product>.toUiModel(productType: ProductType) = map { it.toUiModel(productType) }
