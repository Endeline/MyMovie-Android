package com.endeline.domain.extensions

import com.endeline.data.models.MovieCollection
import com.endeline.data.models.MovieCollection.Dates
import com.endeline.domain.uimodels.MovieCollectionUiModel

fun MovieCollection.toUiModel() = MovieCollectionUiModel(
    page = this@toUiModel.page ?: -1,
    results = this@toUiModel.results?.toUiModel() ?: emptyList(),
    totalPages = this@toUiModel.totalPages ?: -1,
    totalResults = this@toUiModel.totalResults ?: -1,
    dates = this@toUiModel.dates?.toUiModel() ?: Dates.EMPTY.toUiModel()
)
