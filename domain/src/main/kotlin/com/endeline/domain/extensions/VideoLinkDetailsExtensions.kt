package com.endeline.domain.extensions

import com.endeline.data.models.VideoLinks.Link
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel

fun Link.toUiModel() = VideoLinkDetailsUiModel(
        id = this@toUiModel.id ?: "",
        iso_639_1 = this@toUiModel.iso_639_1 ?: "",
        iso_3166_1 = this@toUiModel.iso_3166_1 ?: "",
        key = this@toUiModel.key ?: "",
        name = this@toUiModel.name ?: "",
        site = this@toUiModel.site ?: "",
        size = this@toUiModel.size ?: -1,
        type = this@toUiModel.type ?: ""
    )

fun List<Link>.toUiModel() = this.map { it.toUiModel() }
