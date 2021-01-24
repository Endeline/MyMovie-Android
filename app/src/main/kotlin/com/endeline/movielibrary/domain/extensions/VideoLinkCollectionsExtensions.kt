package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.VideoLinkCollectionUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.VideoLinks

fun VideoLinks.toUiModel() = VideoLinkCollectionUiModel(
    id = id ?: EMPTY_VALUE,
    results = results?.toUiModel() ?: emptyList()
)
