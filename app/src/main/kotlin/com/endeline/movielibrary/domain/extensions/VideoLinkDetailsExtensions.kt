package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.VideoLinkCollectionUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.VideoLinks

fun VideoLinks.Link.toUiModel() = VideoLinkCollectionUiModel.VideoLinkDetailsUiModel(
    id = id ?: EMPTY_TEXT,
    iso_639_1 = iso_639_1 ?: EMPTY_TEXT,
    iso_3166_1 = iso_3166_1 ?: EMPTY_TEXT,
    key = key ?: EMPTY_TEXT,
    name = name ?: EMPTY_TEXT,
    site = site ?: EMPTY_TEXT,
    size = size ?: EMPTY_VALUE,
    type = type ?: EMPTY_TEXT
)

fun List<VideoLinks.Link>.toUiModel() = map { it.toUiModel() }
