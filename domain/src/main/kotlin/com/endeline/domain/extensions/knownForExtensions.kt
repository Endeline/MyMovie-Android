package com.endeline.domain.extensions

import com.endeline.data.models.KnownFor
import com.endeline.domain.uimodels.KnownForUiModel

fun KnownFor.toUiModel() = KnownForUiModel().apply {
    posterPath = this@toUiModel.posterPath
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
}

fun List<KnownFor>.toUiModel() = mutableListOf<KnownForUiModel>().apply {
    this@toUiModel.forEach {
        add(it.toUiModel())
    }
}