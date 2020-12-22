package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.VideoLinks
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel

fun VideoLinks.toUiModel() = VideoLinkCollectionUiModel(
    id = this.id ?: EMPTY_VALUE,
    results = this.results?.toUiModel() ?: emptyList()
)
