package com.endeline.domain.extensions

import com.endeline.data.models.BelongsToCollection
import com.endeline.domain.uimodels.BelongsToCollectionUiModel

fun BelongsToCollection.toUiModel() =
    BelongsToCollectionUiModel().apply {
        id = this@toUiModel.id
        name = this@toUiModel.name
        posterPath = this@toUiModel.posterPath
        backdropPath = this@toUiModel.backdropPath
    }