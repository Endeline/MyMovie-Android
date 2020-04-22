package com.endeline.domain.extensions

import com.endeline.data.models.SearchItem
import com.endeline.domain.uimodels.SearchItemUiModel

fun SearchItem.toUiModel() =
    SearchItemUiModel().apply {
        posterPath = this@toUiModel.posterPath
        popularity = this@toUiModel.popularity
        voteCount = this@toUiModel.voteCount
        video = this@toUiModel.video
        mediaType = this@toUiModel.mediaType
        id = this@toUiModel.id
        adult = this@toUiModel.adult
        backdropPath = this@toUiModel.backdropPath
        originalLanguage = this@toUiModel.originalLanguage
        originalTitle = this@toUiModel.originalTitle
        genreIds = this@toUiModel.genreIds
        title = this@toUiModel.title
        voteAverage = this@toUiModel.voteAverage
        overview = this@toUiModel.overview
        releaseDate = this@toUiModel.releaseDate
        knownForDepartment = this@toUiModel.knownForDepartment
        name = this@toUiModel.name
        knownFor = this@toUiModel.knownFor?.toUiModel()
        profilePath = this@toUiModel.profilePath
        gender = this@toUiModel.gender
    }

fun List<SearchItem>.toUiModel() =
    mutableListOf<SearchItemUiModel>().apply {
        this@toUiModel.forEach {
            add(it.toUiModel())
        }
    }
