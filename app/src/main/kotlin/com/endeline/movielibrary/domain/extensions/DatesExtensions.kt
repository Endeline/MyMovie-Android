package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.data.responses.Products

fun Products.Dates.toUiModel() = ProductsUiModel.DatesUiModel(
    maximum = maximum ?: EMPTY_TEXT,
    minimum = minimum ?: EMPTY_TEXT
)
