package com.endeline.domain.extensions

import com.endeline.data.models.VideoLinkCollection
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel

fun VideoLinkCollection.toUiModel() =
    VideoLinkCollectionUiModel().apply {
        id = this@toUiModel.id
        results = this@toUiModel.results?.toUiModel()
    }
