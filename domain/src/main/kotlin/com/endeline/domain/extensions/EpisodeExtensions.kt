package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.responses.ProductDetails
import com.endeline.domain.uimodels.ProductDetailsUiModel

fun ProductDetails.Episode.toUiModel() = ProductDetailsUiModel.EpisodeUiModel(
    airDate = this.airDate ?: EMPTY_TEXT,
    episodeNumber = this.episodeNumber ?: EMPTY_VALUE,
    id = this.id ?: EMPTY_VALUE,
    name = this.name ?: EMPTY_TEXT,
    overview = this.overview ?: EMPTY_TEXT,
    productionCode = this.productionCode ?: EMPTY_TEXT,
    seasonNumber = this.seasonNumber ?: EMPTY_VALUE,
    stillPath = this.stillPath ?: EMPTY_TEXT,
    voteAverage = this.voteAverage ?: NO_VALUE,
    voteCount = this.voteCount ?: EMPTY_VALUE
)
