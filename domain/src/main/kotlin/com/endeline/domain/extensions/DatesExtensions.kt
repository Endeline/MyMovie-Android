package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.data.responses.Products.Dates
import com.endeline.domain.uimodels.ProductsUiModel.DatesUiModel

fun Dates.toUiModel() = DatesUiModel(
    maximum = this.maximum ?: EMPTY_TEXT,
    minimum = this.minimum ?: EMPTY_TEXT
)
