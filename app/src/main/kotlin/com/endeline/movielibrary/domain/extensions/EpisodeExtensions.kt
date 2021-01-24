package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import com.endeline.movielibrary.data.responses.ProductDetails

fun ProductDetails.Episode.toUiModel() = ProductDetailsUiModel.EpisodeUiModel(
    airDate = airDate ?: EMPTY_TEXT,
    episodeNumber = episodeNumber ?: EMPTY_VALUE,
    id = id ?: EMPTY_VALUE,
    name = name ?: EMPTY_TEXT,
    overview = overview ?: EMPTY_TEXT,
    productionCode = productionCode ?: EMPTY_TEXT,
    seasonNumber = seasonNumber ?: EMPTY_VALUE,
    stillPath = stillPath ?: EMPTY_TEXT,
    voteAverage = voteAverage ?: NO_VALUE,
    voteCount = voteCount ?: EMPTY_VALUE
)
