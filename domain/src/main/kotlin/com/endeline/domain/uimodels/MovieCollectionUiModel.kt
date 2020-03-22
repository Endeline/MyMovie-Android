package com.endeline.domain.uimodels

data class MovieCollectionUiModel (
    var page: Int? = null,
    var results: List<MovieCollectionItemUiModel>? = null,
    var dates: DatesUiModel? = null,
    var totalPages: Int? = null,
    var totalResults: Int? = null
)

