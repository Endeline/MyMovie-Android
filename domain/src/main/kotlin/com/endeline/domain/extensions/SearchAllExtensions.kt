package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.models.Search
import com.endeline.domain.uimodels.SearchUiModel

fun Search.toUiModel() = SearchUiModel(
    page = this@toUiModel.page ?: EMPTY_VALUE,
    totalResults = this@toUiModel.totalResults ?: EMPTY_VALUE,
    totalPages = this@toUiModel.totalPages ?: EMPTY_VALUE,
    results = this@toUiModel.results?.toUiModel() ?: emptyList()
)