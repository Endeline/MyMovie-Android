package com.endeline.domain.extensions

import com.endeline.data.models.MovieCollection.Dates
import com.endeline.domain.uimodels.MovieCollectionUiModel.DatesUiModel

fun Dates.toUiModel() = DatesUiModel(
    maximum = this@toUiModel.maximum ?: "",
    minimum = this@toUiModel.minimum ?: ""
)
