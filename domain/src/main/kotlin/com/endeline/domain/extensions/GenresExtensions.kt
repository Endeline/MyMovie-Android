package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.ProductDetails.Genres
import com.endeline.domain.uimodels.ProductDetailsUiModel.GenresUiModel

fun Genres.toUiModel() = GenresUiModel(
    id = this.id ?: EMPTY_VALUE,
    name = this.name ?: EMPTY_TEXT
)

fun List<Genres>.toUiModel() = this.map { it.toUiModel() }
