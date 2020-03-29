package com.endeline.domain.extensions

import com.endeline.data.models.MovieCollection
import com.endeline.domain.uimodels.MovieCollectionUiModel

fun MovieCollection.toUiModel() =
    MovieCollectionUiModel().apply {
        page = this@toUiModel.page
        results = this@toUiModel.results?.toUiModel()
        dates = this@toUiModel.dates?.toUiModel()
        totalPages = this@toUiModel.totalPages
        totalResults = this@toUiModel.totalResults
    }
