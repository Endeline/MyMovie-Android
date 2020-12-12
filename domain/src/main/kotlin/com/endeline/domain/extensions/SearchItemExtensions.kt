package com.endeline.domain.extensions

import com.endeline.common.Constants.DEFAULT_DATE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.models.Search.SearchItem
import com.endeline.domain.uimodels.SearchUiModel.SearchItemUiModel
import java.util.*

fun SearchItem.toUiModel() = SearchItemUiModel(
    popularity = this@toUiModel.popularity ?: NO_VALUE,
    voteCount = this@toUiModel.voteCount ?: EMPTY_VALUE,
    video = this@toUiModel.video ?: false,
    mediaType = this@toUiModel.mediaType ?: EMPTY_TEXT,
    id = this@toUiModel.id ?: EMPTY_VALUE,
    adult = this@toUiModel.adult ?: false,
    voteAverage = this@toUiModel.voteAverage ?: NO_VALUE,
    gender = this@toUiModel.gender ?: EMPTY_VALUE,
    overview = this@toUiModel.overview ?: EMPTY_TEXT,
    genreIds = this@toUiModel.genreIds ?: emptyList(),
    originalLanguage = this@toUiModel.originalLanguage ?: EMPTY_TEXT,
    posterPath = this@toUiModel.posterPath ?: EMPTY_TEXT,
    releaseDate = this@toUiModel.releaseDate ?: EMPTY_TEXT,
    title = this@toUiModel.title ?: EMPTY_TEXT,
    originalTitle = this@toUiModel.originalTitle ?: EMPTY_TEXT,
    profilePath = this@toUiModel.profilePath ?: EMPTY_TEXT,
    name = this@toUiModel.name ?: EMPTY_TEXT,
    knownForDepartment = this@toUiModel.knownForDepartment ?: EMPTY_TEXT,
    backdropPath = this@toUiModel.backdropPath ?: EMPTY_TEXT,
    knownFor = this@toUiModel.knownFor?.toUiModel() ?: emptyList()
)

fun List<SearchItem>.toUiModel() = this.map { it.toUiModel() }
