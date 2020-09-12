package com.endeline.domain.extensions

import com.endeline.data.models.SearchAll
import com.endeline.domain.uimodels.SearchAllUiModel

fun SearchAll.toUiModel() = SearchAllUiModel(
    page = this@toUiModel.page ?: -1,
    totalResults = this@toUiModel.totalResults ?: -1,
    totalPages = this@toUiModel.totalPages ?: -1,
    results = this@toUiModel.results?.toUiModel() ?: emptyList()
)