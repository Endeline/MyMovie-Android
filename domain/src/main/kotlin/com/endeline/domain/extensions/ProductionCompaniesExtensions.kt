package com.endeline.domain.extensions

import com.endeline.data.models.ProductionCompanies
import com.endeline.domain.uimodels.ProductionCompaniesUiModel

fun ProductionCompanies.toUiModel() =
    ProductionCompaniesUiModel().apply {
        id = this@toUiModel.id
        logoPath = this@toUiModel.logoPath
        name = this@toUiModel.name
        originCountry = this@toUiModel.originCountry
    }

fun List<ProductionCompanies>.toUiModel() =
    mutableListOf<ProductionCompaniesUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }
