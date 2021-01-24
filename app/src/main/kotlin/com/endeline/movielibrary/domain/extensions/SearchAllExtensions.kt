package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.SearchUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.Search

fun Search.toUiModel() = SearchUiModel(
    page = page ?: EMPTY_VALUE,
    totalResults = totalResults ?: EMPTY_VALUE,
    totalPages = totalPages ?: EMPTY_VALUE,
    results = results?.toUiModel() ?: emptyList()
)