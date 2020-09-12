package com.endeline.domain.extensions


import com.endeline.data.models.MovieCollection.MovieItem
import com.endeline.domain.uimodels.MovieCollectionUiModel.MovieItemUiModel
import java.util.*

fun MovieItem.toUiModel() = MovieItemUiModel(
    posterPath = this@toUiModel.posterPath ?: "",
    adult = this@toUiModel.adult ?: false,
    overview = this@toUiModel.overview ?: "",
    releaseDate = this@toUiModel.releaseDate ?: Date(),
    genreIds = this@toUiModel.genreIds ?: emptyList(),
    id = this@toUiModel.id ?: -1,
    originalTitle = this@toUiModel.originalTitle ?: "",
    originalLanguage = this@toUiModel.originalLanguage ?: "",
    title = this@toUiModel.title ?: "",
    backdropPath = this@toUiModel.backdropPath ?: "",
    popularity = this@toUiModel.popularity ?: -1.0,
    voteCount = this@toUiModel.voteCount ?: -1,
    video = this@toUiModel.video ?: false,
    voteAverage = this@toUiModel.voteAverage ?: -1.0
)

fun List<MovieItem>.toUiModel() = this.map { it.toUiModel() }
