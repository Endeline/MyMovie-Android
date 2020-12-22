package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.responses.Search.SearchItem
import com.endeline.domain.uimodels.SearchUiModel.SearchItemUiModel

fun SearchItem.toUiModel() = SearchItemUiModel(
    popularity = this.popularity ?: NO_VALUE,
    voteCount = this.voteCount ?: EMPTY_VALUE,
    video = this.video ?: false,
    mediaType = this.mediaType ?: EMPTY_TEXT,
    id = this.id ?: EMPTY_VALUE,
    adult = this.adult ?: false,
    voteAverage = this.voteAverage ?: NO_VALUE,
    gender = this.gender ?: EMPTY_VALUE,
    overview = this.overview ?: EMPTY_TEXT,
    genreIds = this.genreIds ?: emptyList(),
    originalLanguage = this.originalLanguage ?: EMPTY_TEXT,
    posterPath = this.posterPath ?: EMPTY_TEXT,
    releaseDate = this.releaseDate ?: EMPTY_TEXT,
    title = this.title ?: EMPTY_TEXT,
    originalTitle = this.originalTitle ?: EMPTY_TEXT,
    profilePath = this.profilePath ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT,
    knownForDepartment = this.knownForDepartment ?: EMPTY_TEXT,
    backdropPath = this.backdropPath ?: EMPTY_TEXT,
    knownFor = this.knownFor?.toUiModel() ?: emptyList()
)

fun List<SearchItem>.toUiModel() = this.map { it.toUiModel() }
