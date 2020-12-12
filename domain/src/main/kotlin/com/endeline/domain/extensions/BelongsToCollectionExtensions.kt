package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.models.ProductDetails.BelongsToCollection
import com.endeline.domain.uimodels.ProductDetailsUiModel.BelongsToCollectionUiModel


fun BelongsToCollection.toUiModel() = BelongsToCollectionUiModel(
    id = this@toUiModel.id ?: EMPTY_VALUE,
    name = this@toUiModel.name ?: EMPTY_TEXT,
    posterPath = this@toUiModel.posterPath ?: EMPTY_TEXT,
    backdropPath = this@toUiModel.backdropPath ?: EMPTY_TEXT
)