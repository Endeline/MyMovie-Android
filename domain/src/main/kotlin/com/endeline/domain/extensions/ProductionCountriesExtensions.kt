package com.endeline.domain.extensions

import com.endeline.data.models.MovieDetails.ProductionCountries
import com.endeline.domain.uimodels.MovieDetailsUiModel.ProductionCountriesUiModel

fun ProductionCountries.toUiModel() = ProductionCountriesUiModel(
    iso_3166_1 = this@toUiModel.iso_3166_1 ?: "",
    name = this@toUiModel.name ?: ""
)

fun List<ProductionCountries>.toUiModel() = this.map { it.toUiModel() }
