package com.endeline.domain.extensions

import com.endeline.data.models.Search.KnownFor
import com.endeline.domain.uimodels.SearchUiModel.KnownForUiModel
import java.util.*

fun KnownFor.toUiModel() = KnownForUiModel(
    posterPath = this@toUiModel.posterPath ?: "",
    voteCount = this@toUiModel.voteCount ?: -1,
    video = this@toUiModel.video ?: false,
    mediaType = this@toUiModel.mediaType ?: "",
    id = this@toUiModel.id ?: -1,
    adult = this@toUiModel.adult ?: false,
    originalLanguage = this@toUiModel.originalLanguage ?: "",
    genreIds = this@toUiModel.genreIds ?: emptyList(),
    voteAverage = this@toUiModel.voteAverage ?: -1.0,
    overview = this@toUiModel.overview ?: "",
    releaseDate = this@toUiModel.releaseDate ?: Date(),
    title = this@toUiModel.title ?: "",
    originalTitle = this@toUiModel.originalTitle ?: "",
    backdropPath = this@toUiModel.backdropPath ?: ""
)

fun List<KnownFor>.toUiModel() = this.map { it.toUiModel() }
