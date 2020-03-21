package com.endeline.domain.extensions

import com.endeline.data.pojos.GenresPojo
import com.endeline.domain.uimodels.GenresUiModel

fun GenresPojo.toUiModel(): GenresUiModel =
    GenresUiModel().apply {
        id = this@toUiModel.id
        name = this@toUiModel.name
    }

fun List<GenresPojo>.toUiModel(): List<GenresUiModel> =
    mutableListOf<GenresUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }
