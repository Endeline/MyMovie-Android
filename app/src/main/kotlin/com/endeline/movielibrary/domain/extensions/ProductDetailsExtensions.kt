package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ProductDetailsUiModel
import com.endeline.movielibrary.common.Constants.DEFAULT_DATE
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import com.endeline.movielibrary.data.responses.ProductDetails

fun ProductDetails.toUiModel() = ProductDetailsUiModel(
    adult = adult ?: false,
    backdropPath = backdropPath ?: EMPTY_TEXT,
    budget = budget ?: EMPTY_VALUE,
    genres = genres?.toUiModel() ?: emptyList(),
    homepage = homepage ?: EMPTY_TEXT,
    id = id ?: EMPTY_VALUE,
    imdbId = imdbId ?: EMPTY_TEXT,
    originalLanguage = originalLanguage ?: EMPTY_TEXT,
    originalTitle = originalTitle ?: EMPTY_TEXT,
    overview = overview ?: EMPTY_TEXT,
    popularity = popularity ?: NO_VALUE,
    posterPath = posterPath ?: EMPTY_TEXT,
    productionCountries = productionCountries?.toUiModel() ?: emptyList(),
    releaseDate = releaseDate ?: DEFAULT_DATE,
    revenue = revenue ?: EMPTY_VALUE,
    runtime = runtime ?: EMPTY_VALUE,
    spokenLanguages = spokenLanguages?.toUiModel() ?: emptyList(),
    status = status ?: EMPTY_TEXT,
    tagline = tagline ?: EMPTY_TEXT,
    title = title ?: EMPTY_TEXT,
    video = video ?: false,
    voteAverage = voteAverage ?: NO_VALUE,
    voteCount = voteCount ?: EMPTY_VALUE,
    productionCompanies = productionCompanies?.toUiModel() ?: emptyList(),
    belongsToCollection = belongsToCollection?.toUiModel()
        ?: ProductDetails.BelongsToCollection.EMPTY.toUiModel(),
    createdBy = createdBy?.toUiModel() ?: emptyList(),
    episodeRunTime = episodeRunTime ?: emptyList(),
    inProduction = inProduction ?: false,
    firstAirDate = firstAirDate ?: EMPTY_TEXT,
    lastAirDate = lastAirDate ?: EMPTY_TEXT,
    numberOfEpisodes = numberOfEpisodes ?: EMPTY_VALUE,
    numberOfSeasons = numberOfSeasons ?: EMPTY_VALUE,
    type = type ?: EMPTY_TEXT,
    seasons = seasons?.toUiModel() ?: emptyList(),
    nextEpisodeToAir = nextEpisodeToAir?.toUiModel()
        ?: ProductDetailsUiModel.EpisodeUiModel.EMPTY,
    lastEpisodeToAir = lastEpisodeToAir?.toUiModel()
        ?: ProductDetailsUiModel.EpisodeUiModel.EMPTY,
    name = name ?: EMPTY_TEXT
)
