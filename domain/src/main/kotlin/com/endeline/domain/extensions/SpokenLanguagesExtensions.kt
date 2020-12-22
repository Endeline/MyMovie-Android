package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.data.responses.ProductDetails.SpokenLanguages
import com.endeline.domain.uimodels.ProductDetailsUiModel.SpokenLanguagesUiModel

fun SpokenLanguages.toUiModel() = SpokenLanguagesUiModel(
    iso_639_1 = this.iso_639_1 ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT
)

fun List<SpokenLanguages>.toUiModel() = this.map { it.toUiModel() }
