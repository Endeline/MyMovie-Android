package com.endeline.domain.extensions

import com.endeline.common.Constants.DEFAULT_DATE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.responses.ProductDetails
import com.endeline.data.responses.ProductDetails.BelongsToCollection
import com.endeline.domain.uimodels.ProductDetailsUiModel

fun ProductDetails.toUiModel() = ProductDetailsUiModel(
    adult = this.adult ?: false,
    backdropPath = this.backdropPath ?: EMPTY_TEXT,
    budget = this.budget ?: EMPTY_VALUE,
    genres = this.genres?.toUiModel() ?: emptyList(),
    homepage = this.homepage ?: EMPTY_TEXT,
    id = this.id ?: EMPTY_VALUE,
    imdbId = this.imdbId ?: EMPTY_TEXT,
    originalLanguage = this.originalLanguage ?: EMPTY_TEXT,
    originalTitle = this.originalTitle ?: EMPTY_TEXT,
    overview = this.overview ?: EMPTY_TEXT,
    popularity = this.popularity ?: NO_VALUE,
    posterPath = this.posterPath ?: EMPTY_TEXT,
    productionCountries = this.productionCountries?.toUiModel() ?: emptyList(),
    releaseDate = this.releaseDate ?: DEFAULT_DATE,
    revenue = this.revenue ?: EMPTY_VALUE,
    runtime = this.runtime ?: EMPTY_VALUE,
    spokenLanguages = this.spokenLanguages?.toUiModel() ?: emptyList(),
    status = this.status ?: EMPTY_TEXT,
    tagline = this.tagline ?: EMPTY_TEXT,
    title = this.title ?: EMPTY_TEXT,
    video = this.video ?: false,
    voteAverage = this.voteAverage ?: NO_VALUE,
    voteCount = this.voteCount ?: EMPTY_VALUE,
    productionCompanies = this.productionCompanies?.toUiModel() ?: emptyList(),
    belongsToCollection = this.belongsToCollection?.toUiModel()
        ?: BelongsToCollection.EMPTY.toUiModel(),
    createdBy = this.createdBy?.toUiModel() ?: emptyList(),
    episodeRunTime = this.episodeRunTime ?: emptyList(),
    inProduction = this.inProduction ?: false,
    firstAirDate = this.firstAirDate ?: EMPTY_TEXT,
    lastAirDate = this.lastAirDate ?: EMPTY_TEXT,
    numberOfEpisodes = this.numberOfEpisodes ?: EMPTY_VALUE,
    numberOfSeasons = this.numberOfSeasons ?: EMPTY_VALUE,
    type = this.type ?: EMPTY_TEXT,
    seasons = this.seasons?.toUiModel() ?: emptyList(),
    nextEpisodeToAir = this.nextEpisodeToAir?.toUiModel()
        ?: ProductDetailsUiModel.EpisodeUiModel.EMPTY,
    lastEpisodeToAir = this.lastEpisodeToAir?.toUiModel()
        ?: ProductDetailsUiModel.EpisodeUiModel.EMPTY,
    name = this.name ?: EMPTY_TEXT
)
