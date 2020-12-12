package com.endeline.domain.extensions

import com.endeline.data.models.Products.Dates
import com.endeline.domain.uimodels.ProductsUiModel.DatesUiModel
import java.util.*

fun Dates.toUiModel() = DatesUiModel(
    maximum = this@toUiModel.maximum ?: "",
    minimum = this@toUiModel.minimum ?: ""
)
