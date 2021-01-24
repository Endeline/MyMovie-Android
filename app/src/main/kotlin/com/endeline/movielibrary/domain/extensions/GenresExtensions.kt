package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.ProductDetails

fun ProductDetails.Genres.toUiModel() = ProductDetailsUiModel.GenresUiModel(
    id = id ?: EMPTY_VALUE,
    name = name ?: EMPTY_TEXT
)

fun List<ProductDetails.Genres>.toUiModel() = map { it.toUiModel() }
