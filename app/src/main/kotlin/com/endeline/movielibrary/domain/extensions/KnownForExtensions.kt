package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.SearchUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import com.endeline.movielibrary.data.responses.Search

fun Search.KnownFor.toUiModel() = SearchUiModel.KnownForUiModel(
    posterPath = posterPath ?: EMPTY_TEXT,
    voteCount = voteCount ?: EMPTY_VALUE,
    video = video ?: false,
    mediaType = mediaType ?: EMPTY_TEXT,
    id = id ?: EMPTY_VALUE,
    adult = adult ?: false,
    originalLanguage = originalLanguage ?: EMPTY_TEXT,
    genreIds = genreIds ?: emptyList(),
    voteAverage = voteAverage ?: NO_VALUE,
    overview = overview ?: EMPTY_TEXT,
    releaseDate = releaseDate ?: EMPTY_TEXT,
    title = title ?: EMPTY_TEXT,
    originalTitle = originalTitle ?: EMPTY_TEXT,
    backdropPath = backdropPath ?: EMPTY_TEXT
)

fun List<Search.KnownFor>.toUiModel() = map { it.toUiModel() }
