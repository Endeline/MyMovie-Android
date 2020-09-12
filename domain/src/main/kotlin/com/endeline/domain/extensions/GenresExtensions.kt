package com.endeline.domain.extensions

import com.endeline.data.models.MovieDetails.Genres
import com.endeline.domain.uimodels.MovieDetailsUiModel.GenresUiModel

fun Genres.toUiModel() = GenresUiModel(
    id = this@toUiModel.id ?: -1,
    name = this@toUiModel.name ?: ""
)

fun List<Genres>.toUiModel() = this.map { it.toUiModel() }
