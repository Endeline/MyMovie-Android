package com.endeline.domain.extensions

import com.endeline.data.models.Dates
import com.endeline.domain.uimodels.DatesUiModel

fun Dates.toUiModel(): DatesUiModel =
    DatesUiModel().apply {
        maximum = this@toUiModel.maximum
        minimum = this@toUiModel.minimum
    }
