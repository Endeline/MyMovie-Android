package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ImagesUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import com.endeline.movielibrary.data.responses.Images

fun Images.Image.toUiModel() = ImagesUiModel.ImageUiModel(
    aspectRatio = aspectRatio ?: NO_VALUE,
    filePath = filePath ?: EMPTY_TEXT,
    height = height ?: EMPTY_VALUE,
    iso_639_1 = iso_639_1 ?: EMPTY_TEXT,
    voteAverage = voteAverage ?: NO_VALUE,
    voteCount = voteCount ?: EMPTY_VALUE,
    width = width ?: EMPTY_VALUE
)

fun List<Images.Image>.toUiModel() = map { it.toUiModel() }

fun Images.toUiModel() = ImagesUiModel(
    id = id ?: -1,
    posters = posters?.toUiModel() ?: emptyList(),
    backdrops = backdrops?.toUiModel() ?: emptyList(),
    profiles = profiles?.toUiModel() ?: emptyList()
)
