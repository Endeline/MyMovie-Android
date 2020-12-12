package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.models.VideoLinks.Link
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel

fun Link.toUiModel() = VideoLinkDetailsUiModel(
    id = this@toUiModel.id ?: EMPTY_TEXT,
    iso_639_1 = this@toUiModel.iso_639_1 ?: EMPTY_TEXT,
    iso_3166_1 = this@toUiModel.iso_3166_1 ?: EMPTY_TEXT,
    key = this@toUiModel.key ?: EMPTY_TEXT,
    name = this@toUiModel.name ?: EMPTY_TEXT,
    site = this@toUiModel.site ?: EMPTY_TEXT,
    size = this@toUiModel.size ?: EMPTY_VALUE,
    type = this@toUiModel.type ?: EMPTY_TEXT
)

fun List<Link>.toUiModel() = this.map { it.toUiModel() }
