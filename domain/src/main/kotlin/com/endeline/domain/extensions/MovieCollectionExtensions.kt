package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.ProductType
import com.endeline.data.models.Products
import com.endeline.data.models.Products.Dates
import com.endeline.domain.uimodels.ProductsUiModel

fun Products.toUiModel(productType: ProductType) = ProductsUiModel(
    page = this@toUiModel.page ?: EMPTY_VALUE,
    results = this@toUiModel.results?.toUiModel(productType) ?: emptyList(),
    totalPages = this@toUiModel.totalPages ?: EMPTY_VALUE,
    totalResults = this@toUiModel.totalResults ?: EMPTY_VALUE,
    dates = this@toUiModel.dates?.toUiModel() ?: Dates.EMPTY.toUiModel()
)
