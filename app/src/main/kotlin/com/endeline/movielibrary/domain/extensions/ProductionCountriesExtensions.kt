package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.data.responses.ProductDetails

fun ProductDetails.ProductionCountries.toUiModel() =
    ProductDetailsUiModel.ProductionCountriesUiModel(
        iso_3166_1 = iso_3166_1 ?: EMPTY_TEXT,
        name = name ?: EMPTY_TEXT
    )

fun List<ProductDetails.ProductionCountries>.toUiModel() = map { it.toUiModel() }
