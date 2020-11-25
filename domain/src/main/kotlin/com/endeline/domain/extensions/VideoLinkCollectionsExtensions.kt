package com.endeline.domain.extensions

import com.endeline.data.models.VideoLinks
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel

fun VideoLinks.toUiModel() = VideoLinkCollectionUiModel(
    id = this@toUiModel.id ?: -1,
    results = this@toUiModel.results?.toUiModel() ?: emptyList()
)
