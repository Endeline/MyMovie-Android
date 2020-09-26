package com.endeline.domain.extensions

import com.endeline.data.models.ProductDetails.Genres
import com.endeline.domain.uimodels.ProductDetailsUiModel.GenresUiModel

fun Genres.toUiModel() = GenresUiModel(
    id = this@toUiModel.id ?: -1,
    name = this@toUiModel.name ?: ""
)

fun List<Genres>.toUiModel() = this.map { it.toUiModel() }
