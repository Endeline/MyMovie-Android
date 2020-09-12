package com.endeline.domain.extensions

import com.endeline.data.models.MovieDetails
import com.endeline.data.models.MovieDetails.BelongsToCollection
import com.endeline.domain.uimodels.MovieDetailsUiModel
import java.util.*

fun MovieDetails.toUiModel() = MovieDetailsUiModel(
    adult = this@toUiModel.adult ?: false,
    backdropPath = this@toUiModel.backdropPath ?: "",
    budget = this@toUiModel.budget ?: -1,
    genres = this@toUiModel.genres?.toUiModel() ?: emptyList(),
    homepage = this@toUiModel.homepage ?: "",
    id = this@toUiModel.id ?: -1,
    imdbId = this@toUiModel.imdbId ?: "",
    originalLanguage = this@toUiModel.originalLanguage ?: "",
    originalTitle = this@toUiModel.originalTitle ?: "",
    overview = this@toUiModel.overview ?: "",
    popularity = this@toUiModel.popularity ?: -1.0,
    posterPath = this@toUiModel.posterPath ?: "",
    productionCountries = this@toUiModel.productionCountries?.toUiModel() ?: emptyList(),
    releaseDate = this@toUiModel.releaseDate ?: Date(),
    revenue = this@toUiModel.revenue ?: -1,
    runtime = this@toUiModel.runtime ?: -1,
    spokenLanguages = this@toUiModel.spokenLanguages?.toUiModel() ?: emptyList(),
    status = this@toUiModel.status ?: "",
    tagline = this@toUiModel.tagline ?: "",
    title = this@toUiModel.title ?: "",
    video = this@toUiModel.video ?: false,
    voteAverage = this@toUiModel.voteAverage ?: -1.0,
    voteCount = this@toUiModel.voteCount ?: -1,
    productionCompanies = this@toUiModel.productionCompanies?.toUiModel() ?: emptyList(),
    belongsToCollection = this@toUiModel.belongsToCollection?.toUiModel()
        ?: BelongsToCollection.EMPTY.toUiModel()
)
