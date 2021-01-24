package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.SearchUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import com.endeline.movielibrary.data.responses.Search

fun Search.SearchItem.toUiModel() = SearchUiModel.SearchItemUiModel(
    popularity = popularity ?: NO_VALUE,
    voteCount = voteCount ?: EMPTY_VALUE,
    video = video ?: false,
    mediaType = mediaType ?: EMPTY_TEXT,
    id = id ?: EMPTY_VALUE,
    adult = adult ?: false,
    voteAverage = voteAverage ?: NO_VALUE,
    gender = gender ?: EMPTY_VALUE,
    overview = overview ?: EMPTY_TEXT,
    genreIds = genreIds ?: emptyList(),
    originalLanguage = originalLanguage ?: EMPTY_TEXT,
    posterPath = posterPath ?: EMPTY_TEXT,
    releaseDate = releaseDate ?: EMPTY_TEXT,
    title = title ?: EMPTY_TEXT,
    originalTitle = originalTitle ?: EMPTY_TEXT,
    profilePath = profilePath ?: EMPTY_TEXT,
    name = name ?: EMPTY_TEXT,
    knownForDepartment = knownForDepartment ?: EMPTY_TEXT,
    backdropPath = backdropPath ?: EMPTY_TEXT,
    knownFor = knownFor?.toUiModel() ?: emptyList()
)

fun List<Search.SearchItem>.toUiModel() = map { it.toUiModel() }
