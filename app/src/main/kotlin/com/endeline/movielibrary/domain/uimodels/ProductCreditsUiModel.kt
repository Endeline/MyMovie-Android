package com.endeline.movielibrary.domain.uimodels

data class ProductCreditsUiModel(
    val id: Int,
    val cast: List<ProductUiModel>,
    val crew: List<ProductUiModel>
)
