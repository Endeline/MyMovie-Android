package com.endeline.domain.uimodels

data class ProductsUiModel(
    val page: Int,
    val results: List<ProductUiModel>,
    val dates: DatesUiModel,
    val totalPages: Int,
    val totalResults: Int
) {
    data class DatesUiModel(
        val maximum: String,
        val minimum: String
    )
}
