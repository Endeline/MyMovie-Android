package com.endeline.domain.extensions

import com.endeline.data.models.VideoLinkDetails
import com.endeline.domain.uimodels.VideoLinkDetailsUiModel

fun VideoLinkDetails.toUiModel(): VideoLinkDetailsUiModel =
    VideoLinkDetailsUiModel().apply {
        id = this@toUiModel.id
        iso_639_1 = this@toUiModel.iso_639_1
        iso_3166_1 = this@toUiModel.iso_3166_1
        key = this@toUiModel.key
        name = this@toUiModel.name
        site = this@toUiModel.site
        size = this@toUiModel.size
        type = this@toUiModel.type
    }

fun List<VideoLinkDetails>.toUiModel(): List<VideoLinkDetailsUiModel> =
    mutableListOf<VideoLinkDetailsUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }
