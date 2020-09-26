package com.endeline.domain.extensions

import com.endeline.data.models.Products.Dates
import com.endeline.domain.uimodels.ProductsUiModel.DatesUiModel

fun Dates.toUiModel() = DatesUiModel(
    maximum = this@toUiModel.maximum ?: "",
    minimum = this@toUiModel.minimum ?: ""
)
