package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.ProductDetails

fun ProductDetails.Season.toUiModel() = ProductDetailsUiModel.SeasonUiModel(
    airDate = airDate ?: EMPTY_TEXT,
    episodeCount = episodeCount ?: EMPTY_VALUE,
    id = id ?: EMPTY_VALUE,
    name = name ?: EMPTY_TEXT,
    overview = overview ?: EMPTY_TEXT,
    posterPath = posterPath ?: EMPTY_TEXT,
    seasonNumber = seasonNumber ?: EMPTY_VALUE
)

fun List<ProductDetails.Season>.toUiModel() = map { it.toUiModel() }
