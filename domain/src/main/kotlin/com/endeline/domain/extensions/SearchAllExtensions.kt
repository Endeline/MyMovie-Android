package com.endeline.domain.extensions

import com.endeline.data.models.Search
import com.endeline.domain.uimodels.SearchUiModel

fun Search.toUiModel() = SearchUiModel(
    page = this@toUiModel.page ?: -1,
    totalResults = this@toUiModel.totalResults ?: -1,
    totalPages = this@toUiModel.totalPages ?: -1,
    results = this@toUiModel.results?.toUiModel() ?: emptyList()
)