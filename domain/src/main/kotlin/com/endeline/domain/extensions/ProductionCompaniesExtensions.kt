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

fun ProductionCompaniesUiModel.toEntity() =
    ProductionCompanies(
        id = this.id ?: 0,
        logoPath = this.logoPath ?: "",
        name = this.name ?: "",
        originCountry = this.originCountry ?: ""
    )

fun List<ProductionCompaniesUiModel>.toEntity() =
    mutableListOf<ProductionCompanies>().apply {
        this@toEntity.forEach {
            add(it.toEntity())
        }
    }
