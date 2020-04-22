package com.endeline.domain.uimodels

data class SearchAllUiModel(
    var page: Int? = null,
    var totalResults: Int? = null,
    var totalPages: Int? = null,
    var results: List<SearchItemUiModel>? = null
)
