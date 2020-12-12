package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.models.VideoLinks
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel

fun VideoLinks.toUiModel() = VideoLinkCollectionUiModel(
    id = this@toUiModel.id ?: EMPTY_VALUE,
    results = this@toUiModel.results?.toUiModel() ?: emptyList()
)
