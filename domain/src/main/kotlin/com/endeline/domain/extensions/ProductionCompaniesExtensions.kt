package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.ProductDetails.ProductionCompanies
import com.endeline.domain.uimodels.ProductDetailsUiModel.ProductionCompaniesUiModel

fun ProductionCompanies.toUiModel() = ProductionCompaniesUiModel(
    id = this.id ?: EMPTY_VALUE,
    logoPath = this.logoPath ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT,
    originCountry = this.originCountry ?: EMPTY_TEXT
)

fun List<ProductionCompanies>.toUiModel() = this.map { it.toUiModel() }
