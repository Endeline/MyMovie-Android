package com.endeline.domain.extensions

import com.endeline.common.Constants.DEFAULT_DATE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.models.Search.KnownFor
import com.endeline.domain.uimodels.SearchUiModel.KnownForUiModel
import java.util.*

fun KnownFor.toUiModel() = KnownForUiModel(
    posterPath = this@toUiModel.posterPath ?: EMPTY_TEXT,
    voteCount = this@toUiModel.voteCount ?: EMPTY_VALUE,
    video = this@toUiModel.video ?: false,
    mediaType = this@toUiModel.mediaType ?: EMPTY_TEXT,
    id = this@toUiModel.id ?: EMPTY_VALUE,
    adult = this@toUiModel.adult ?: false,
    originalLanguage = this@toUiModel.originalLanguage ?: EMPTY_TEXT,
    genreIds = this@toUiModel.genreIds ?: emptyList(),
    voteAverage = this@toUiModel.voteAverage ?: NO_VALUE,
    overview = this@toUiModel.overview ?: EMPTY_TEXT,
    releaseDate = this@toUiModel.releaseDate ?: DEFAULT_DATE,
    title = this@toUiModel.title ?: EMPTY_TEXT,
    originalTitle = this@toUiModel.originalTitle ?: EMPTY_TEXT,
    backdropPath = this@toUiModel.backdropPath ?: EMPTY_TEXT
)

fun List<KnownFor>.toUiModel() = this.map { it.toUiModel() }
