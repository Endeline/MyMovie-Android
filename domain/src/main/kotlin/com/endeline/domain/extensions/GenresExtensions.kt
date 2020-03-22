package com.endeline.domain.extensions

import com.endeline.data.models.Genres
import com.endeline.domain.uimodels.GenresUiModel

fun Genres.toUiModel(): GenresUiModel =
    GenresUiModel().apply {
        id = this@toUiModel.id
        name = this@toUiModel.name
    }

fun List<Genres>.toUiModel(): List<GenresUiModel> =
    mutableListOf<GenresUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }
