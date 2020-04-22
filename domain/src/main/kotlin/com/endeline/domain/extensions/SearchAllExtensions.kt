package com.endeline.domain.extensions

import com.endeline.data.models.SearchAll
import com.endeline.domain.uimodels.SearchAllUiModel

fun SearchAll.toUiModel() =
    SearchAllUiModel().apply {
        page = this@toUiModel.page
        totalResults = this@toUiModel.totalResults
        totalPages = this@toUiModel.totalPages
        results = this@toUiModel.results?.toUiModel()
    }