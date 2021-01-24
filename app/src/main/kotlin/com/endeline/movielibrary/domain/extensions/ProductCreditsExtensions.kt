package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductCreditsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.types.ProductType
import com.endeline.movielibrary.data.responses.ProductCredits

fun ProductCredits.toUiModel(productType: ProductType) = ProductCreditsUiModel(
    id = id ?: EMPTY_VALUE,
    cast = cast?.toUiModel(productType) ?: emptyList(),
    crew = crew?.toUiModel(productType) ?: emptyList()
)
