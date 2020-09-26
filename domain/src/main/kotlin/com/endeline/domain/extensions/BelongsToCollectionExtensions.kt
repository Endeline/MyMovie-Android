package com.endeline.domain.extensions

import com.endeline.data.models.ProductDetails.BelongsToCollection
import com.endeline.domain.uimodels.ProductDetailsUiModel.BelongsToCollectionUiModel


fun BelongsToCollection.toUiModel() = BelongsToCollectionUiModel(
    id = this@toUiModel.id ?: -1,
    name = this@toUiModel.name ?: "",
    posterPath = this@toUiModel.posterPath ?: "",
    backdropPath = this@toUiModel.backdropPath ?: ""
)