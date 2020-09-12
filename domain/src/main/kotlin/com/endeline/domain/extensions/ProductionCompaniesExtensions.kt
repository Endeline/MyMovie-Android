package com.endeline.domain.extensions

import com.endeline.data.models.MovieDetails.ProductionCompanies
import com.endeline.domain.uimodels.MovieDetailsUiModel.ProductionCompaniesUiModel

fun ProductionCompanies.toUiModel() = ProductionCompaniesUiModel(
    id = this@toUiModel.id ?: -1,
    logoPath = this@toUiModel.logoPath ?: "",
    name = this@toUiModel.name ?: "",
    originCountry = this@toUiModel.originCountry ?: ""
)

fun List<ProductionCompanies>.toUiModel() = this.map { it.toUiModel() }
