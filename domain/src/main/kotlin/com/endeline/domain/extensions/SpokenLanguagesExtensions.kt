package com.endeline.domain.extensions

import com.endeline.data.models.ProductDetails.SpokenLanguages
import com.endeline.domain.uimodels.ProductDetailsUiModel.SpokenLanguagesUiModel

fun SpokenLanguages.toUiModel() = SpokenLanguagesUiModel(
    iso_639_1 = this@toUiModel.iso_639_1 ?: "",
    name = this@toUiModel.name ?: ""
)

fun List<SpokenLanguages>.toUiModel() = this.map { it.toUiModel() }
