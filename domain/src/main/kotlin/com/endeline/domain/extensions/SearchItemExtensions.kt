package com.endeline.domain.extensions

import com.endeline.data.models.SearchAll.SearchItem
import com.endeline.domain.uimodels.SearchAllUiModel.SearchItemUiModel
import java.util.*

fun SearchItem.toUiModel() = SearchItemUiModel(
    popularity = this@toUiModel.popularity ?: -1.0,
    voteCount = this@toUiModel.voteCount ?: -1,
    video = this@toUiModel.video ?: false,
    mediaType = this@toUiModel.mediaType ?: "",
    id = this@toUiModel.id ?: -1,
    adult = this@toUiModel.adult ?: false,
    voteAverage = this@toUiModel.voteAverage ?: -1.0,
    gender = this@toUiModel.gender ?: -1,
    overview = this@toUiModel.overview ?: "",
    genreIds = this@toUiModel.genreIds ?: emptyList(),
    originalLanguage = this@toUiModel.originalLanguage ?: "",
    posterPath = this@toUiModel.posterPath ?: "",
    releaseDate = this@toUiModel.releaseDate ?: Date(),
    title = this@toUiModel.title ?: "",
    originalTitle = this@toUiModel.originalTitle ?: "",
    profilePath = this@toUiModel.profilePath ?: "",
    name = this@toUiModel.name ?: "",
    knownForDepartment = this@toUiModel.knownForDepartment ?: "",
    backdropPath = this@toUiModel.backdropPath ?: "",
    knownFor = this@toUiModel.knownFor?.toUiModel() ?: emptyList()
)

fun List<SearchItem>.toUiModel() = this.map { it.toUiModel() }
