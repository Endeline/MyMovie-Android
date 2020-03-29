package com.endeline.domain.extensions

import com.endeline.data.models.MovieCollectionItem
import com.endeline.domain.uimodels.MovieCollectionItemUiModel

fun MovieCollectionItem.toUiModel() =
    MovieCollectionItemUiModel().apply {
        posterPath = this@toUiModel.posterPath
        adult = this@toUiModel.adult
        overview = this@toUiModel.overview
        releaseDate = this@toUiModel.releaseDate
        genreIds = this@toUiModel.genreIds
        id = this@toUiModel.id
        originalTitle = this@toUiModel.originalTitle
        originalLanguage = this@toUiModel.originalLanguage
        title = this@toUiModel.title
        backdropPath = this@toUiModel.backdropPath
        popularity = this@toUiModel.popularity
        voteCount = this@toUiModel.voteCount
        video = this@toUiModel.video
        voteAverage = this@toUiModel.voteAverage
    }

fun List<MovieCollectionItem>.toUiModel() =
    mutableListOf<MovieCollectionItemUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }
