package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.ProductDetails


fun ProductDetails.BelongsToCollection.toUiModel() =
    ProductDetailsUiModel.BelongsToCollectionUiModel(
        id = id ?: EMPTY_VALUE,
        name = name ?: EMPTY_TEXT,
        posterPath = posterPath ?: EMPTY_TEXT,
        backdropPath = backdropPath ?: EMPTY_TEXT
    )