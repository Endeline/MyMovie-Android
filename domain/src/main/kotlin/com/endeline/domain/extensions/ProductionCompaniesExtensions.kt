package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.models.ProductDetails.ProductionCompanies
import com.endeline.domain.uimodels.ProductDetailsUiModel.ProductionCompaniesUiModel

fun ProductionCompanies.toUiModel() = ProductionCompaniesUiModel(
    id = this@toUiModel.id ?: EMPTY_VALUE,
    logoPath = this@toUiModel.logoPath ?: EMPTY_TEXT,
    name = this@toUiModel.name ?: EMPTY_TEXT,
    originCountry = this@toUiModel.originCountry ?: EMPTY_TEXT
)

fun List<ProductionCompanies>.toUiModel() = this.map { it.toUiModel() }
