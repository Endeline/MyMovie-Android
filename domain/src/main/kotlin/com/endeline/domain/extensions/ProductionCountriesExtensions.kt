package com.endeline.domain.extensions

import com.endeline.data.models.ProductionCountries
import com.endeline.domain.uimodels.ProductionCountriesUiModel

fun ProductionCountries.toUiModel() =
    ProductionCountriesUiModel().apply {
        iso_3166_1 = this@toUiModel.iso_3166_1
        name = this@toUiModel.name
    }

fun List<ProductionCountries>.toUiModel() =
    mutableListOf<ProductionCountriesUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }

fun ProductionCountriesUiModel.toEntity() =
    ProductionCountries(
        iso_3166_1 = this.iso_3166_1 ?: "",
        name = this.name ?: ""
    )

fun List<ProductionCountriesUiModel>.toEntity() =
    mutableListOf<ProductionCountries>().apply {
        this@toEntity.forEach {
            add(it.toEntity())
        }
    }
