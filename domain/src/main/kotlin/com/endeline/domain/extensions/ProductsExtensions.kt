package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.types.ProductType
import com.endeline.data.responses.Products
import com.endeline.data.responses.Products.Dates
import com.endeline.domain.uimodels.ProductsUiModel

fun Products.toUiModel(productType: ProductType) = ProductsUiModel(
    page = this.page ?: EMPTY_VALUE,
    results = this.results?.toUiModel(productType) ?: emptyList(),
    totalPages = this.totalPages ?: EMPTY_VALUE,
    totalResults = this.totalResults ?: EMPTY_VALUE,
    dates = this.dates?.toUiModel() ?: Dates.EMPTY.toUiModel()
)
