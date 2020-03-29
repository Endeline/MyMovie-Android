package com.endeline.domain.extensions

import com.endeline.data.models.SpokenLanguages
import com.endeline.domain.uimodels.SpokenLanguagesUiModel

fun SpokenLanguages.toUiModel() =
    SpokenLanguagesUiModel().apply {
        iso_639_1 = this@toUiModel.iso_639_1
        name = this@toUiModel.name
    }

fun List<SpokenLanguages>.toUiModel() =
    mutableListOf<SpokenLanguagesUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }
