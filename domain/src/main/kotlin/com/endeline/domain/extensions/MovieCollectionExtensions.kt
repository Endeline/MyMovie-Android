package com.endeline.domain.extensions

import com.endeline.data.models.Products
import com.endeline.data.models.Products.Dates
import com.endeline.domain.ProductType
import com.endeline.domain.uimodels.ProductsUiModel

fun Products.toUiModel(productType: ProductType) = ProductsUiModel(
    page = this@toUiModel.page ?: -1,
    results = this@toUiModel.results?.toUiModel(productType) ?: emptyList(),
    totalPages = this@toUiModel.totalPages ?: -1,
    totalResults = this@toUiModel.totalResults ?: -1,
    dates = this@toUiModel.dates?.toUiModel() ?: Dates.EMPTY.toUiModel()
)
