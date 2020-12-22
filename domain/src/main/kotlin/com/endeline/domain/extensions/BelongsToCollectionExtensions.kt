package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.ProductDetails.BelongsToCollection
import com.endeline.domain.uimodels.ProductDetailsUiModel.BelongsToCollectionUiModel


fun BelongsToCollection.toUiModel() = BelongsToCollectionUiModel(
    id = this.id ?: EMPTY_VALUE,
    name = this.name ?: EMPTY_TEXT,
    posterPath = this.posterPath ?: EMPTY_TEXT,
    backdropPath = this.backdropPath ?: EMPTY_TEXT
)