package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.data.models.Products.Dates
import com.endeline.domain.uimodels.ProductsUiModel.DatesUiModel
import java.util.*

fun Dates.toUiModel() = DatesUiModel(
    maximum = this@toUiModel.maximum ?: EMPTY_TEXT,
    minimum = this@toUiModel.minimum ?: EMPTY_TEXT
)
