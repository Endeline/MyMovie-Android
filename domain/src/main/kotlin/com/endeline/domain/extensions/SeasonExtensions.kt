package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.ProductDetails.Season
import com.endeline.domain.uimodels.ProductDetailsUiModel

fun Season.toUiModel() = ProductDetailsUiModel.SeasonUiModel(
    airDate = this.airDate ?: EMPTY_TEXT,
    episodeCount = this.episodeCount ?: EMPTY_VALUE,
    id = this.id ?: EMPTY_VALUE,
    name = this.name ?: EMPTY_TEXT,
    overview = this.overview ?: EMPTY_TEXT,
    posterPath = this.posterPath ?: EMPTY_TEXT,
    seasonNumber = this.seasonNumber ?: EMPTY_VALUE
)

fun List<Season>.toUiModel() = this.map { it.toUiModel() }
