package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.VideoLinks.Link
import com.endeline.domain.uimodels.VideoLinkCollectionUiModel.VideoLinkDetailsUiModel

fun Link.toUiModel() = VideoLinkDetailsUiModel(
    id = this.id ?: EMPTY_TEXT,
    iso_639_1 = this.iso_639_1 ?: EMPTY_TEXT,
    iso_3166_1 = this.iso_3166_1 ?: EMPTY_TEXT,
    key = this.key ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT,
    site = this.site ?: EMPTY_TEXT,
    size = this.size ?: EMPTY_VALUE,
    type = this.type ?: EMPTY_TEXT
)

fun List<Link>.toUiModel() = this.map { it.toUiModel() }
