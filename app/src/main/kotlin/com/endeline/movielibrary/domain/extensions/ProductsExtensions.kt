package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.data.responses.Products

fun Products.toUiModel(productType: ProductType) = ProductsUiModel(
    page = page ?: EMPTY_VALUE,
    results = results?.toUiModel(productType) ?: emptyList(),
    totalPages = totalPages ?: EMPTY_VALUE,
    totalResults = totalResults ?: EMPTY_VALUE,
    dates = dates?.toUiModel() ?: Products.Dates.EMPTY.toUiModel()
)
