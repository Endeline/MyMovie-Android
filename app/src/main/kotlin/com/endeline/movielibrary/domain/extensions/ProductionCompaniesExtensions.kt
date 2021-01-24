package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.ProductDetails

fun ProductDetails.ProductionCompanies.toUiModel() =
    ProductDetailsUiModel.ProductionCompaniesUiModel(
        id = id ?: EMPTY_VALUE,
        logoPath = logoPath ?: EMPTY_TEXT,
        name = name ?: EMPTY_TEXT,
        originCountry = originCountry ?: EMPTY_TEXT
    )

fun List<ProductDetails.ProductionCompanies>.toUiModel() = map { it.toUiModel() }
