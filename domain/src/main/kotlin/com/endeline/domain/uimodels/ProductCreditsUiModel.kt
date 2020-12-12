package com.endeline.domain.uimodels

data class ProductCreditsUiModel(
    val id: Int,
    val cast: List<ProductUiModel>,
    val crew: List<ProductUiModel>
)
