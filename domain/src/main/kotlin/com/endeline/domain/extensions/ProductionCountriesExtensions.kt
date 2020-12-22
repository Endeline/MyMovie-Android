package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.data.responses.ProductDetails.ProductionCountries
import com.endeline.domain.uimodels.ProductDetailsUiModel.ProductionCountriesUiModel

fun ProductionCountries.toUiModel() = ProductionCountriesUiModel(
    iso_3166_1 = this.iso_3166_1 ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT
)

fun List<ProductionCountries>.toUiModel() = this.map { it.toUiModel() }
