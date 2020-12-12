package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.types.ProductType
import com.endeline.data.models.ProductCredits
import com.endeline.domain.uimodels.ProductCreditsUiModel

fun ProductCredits.toUiModel(productType: ProductType) = ProductCreditsUiModel(
    id = this.id ?: EMPTY_VALUE,
    cast = this.cast?.toUiModel(productType) ?: emptyList(),
    crew = this.crew?.toUiModel(productType) ?: emptyList()
)
