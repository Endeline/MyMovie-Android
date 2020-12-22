package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.responses.Images
import com.endeline.domain.uimodels.ImagesUiModel

fun Images.Image.toUiModel() = ImagesUiModel.ImageUiModel(
    aspectRatio = this.aspectRatio ?: NO_VALUE,
    filePath = this.filePath ?: EMPTY_TEXT,
    height = this.height ?: EMPTY_VALUE,
    iso_639_1 = this.iso_639_1 ?: EMPTY_TEXT,
    voteAverage = this.voteAverage ?: NO_VALUE,
    voteCount = this.voteCount ?: EMPTY_VALUE,
    width = this.width ?: EMPTY_VALUE
)

fun List<Images.Image>.toUiModel() = this.map { it.toUiModel() }

fun Images.toUiModel() = ImagesUiModel(
    id = this.id ?: -1,
    posters = this.posters?.toUiModel() ?: emptyList(),
    backdrops = this.backdrops?.toUiModel() ?: emptyList(),
    profiles = this.profiles?.toUiModel() ?: emptyList()
)
