package com.endeline.domain.extensions

import com.endeline.data.models.Images
import com.endeline.domain.uimodels.ImagesUiModel

fun Images.Image.toUiModel() = ImagesUiModel.ImageUiModel(
    aspectRatio = this.aspectRatio ?: -1.0,
    filePath = this.filePath ?: "",
    height = this.height ?: -1,
    iso_639_1 = this.iso_639_1 ?: "",
    voteAverage = this.voteAverage ?: -1.0,
    voteCount = this.voteCount ?: -1,
    width = this.width ?: -1
)

fun List<Images.Image>.toUiModel() = this.map { it.toUiModel() }

fun Images.toUiModel() = ImagesUiModel(
    id = this.id ?: -1,
    posters = this.posters?.toUiModel() ?: emptyList(),
    backdrops = this.backdrops?.toUiModel() ?: emptyList(),
    profiles = this.profiles?.toUiModel() ?: emptyList()
)
