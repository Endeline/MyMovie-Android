package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.responses.Search.KnownFor
import com.endeline.domain.uimodels.SearchUiModel.KnownForUiModel

fun KnownFor.toUiModel() = KnownForUiModel(
    posterPath = this.posterPath ?: EMPTY_TEXT,
    voteCount = this.voteCount ?: EMPTY_VALUE,
    video = this.video ?: false,
    mediaType = this.mediaType ?: EMPTY_TEXT,
    id = this.id ?: EMPTY_VALUE,
    adult = this.adult ?: false,
    originalLanguage = this.originalLanguage ?: EMPTY_TEXT,
    genreIds = this.genreIds ?: emptyList(),
    voteAverage = this.voteAverage ?: NO_VALUE,
    overview = this.overview ?: EMPTY_TEXT,
    releaseDate = this.releaseDate ?: EMPTY_TEXT,
    title = this.title ?: EMPTY_TEXT,
    originalTitle = this.originalTitle ?: EMPTY_TEXT,
    backdropPath = this.backdropPath ?: EMPTY_TEXT
)

fun List<KnownFor>.toUiModel() = this.map { it.toUiModel() }
