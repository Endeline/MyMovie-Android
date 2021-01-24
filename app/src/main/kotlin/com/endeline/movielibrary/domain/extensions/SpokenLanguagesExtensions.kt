package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.data.responses.ProductDetails

fun ProductDetails.SpokenLanguages.toUiModel() = ProductDetailsUiModel.SpokenLanguagesUiModel(
    iso_639_1 = iso_639_1 ?: EMPTY_TEXT,
    name = name ?: EMPTY_TEXT
)

fun List<ProductDetails.SpokenLanguages>.toUiModel() = map { it.toUiModel() }
