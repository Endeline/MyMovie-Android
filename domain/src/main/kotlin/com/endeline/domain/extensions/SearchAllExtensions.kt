package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.Search
import com.endeline.domain.uimodels.SearchUiModel

fun Search.toUiModel() = SearchUiModel(
    page = this.page ?: EMPTY_VALUE,
    totalResults = this.totalResults ?: EMPTY_VALUE,
    totalPages = this.totalPages ?: EMPTY_VALUE,
    results = this.results?.toUiModel() ?: emptyList()
)