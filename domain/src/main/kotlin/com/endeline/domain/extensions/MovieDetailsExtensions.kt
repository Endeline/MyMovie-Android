package com.endeline.domain.extensions

import com.endeline.data.models.MovieDetails
import com.endeline.domain.uimodels.MovieDetailsUiModel

fun MovieDetails.toUiModel() =
    MovieDetailsUiModel().apply {
        adult = this@toUiModel.adult
        backdropPath = this@toUiModel.backdropPath
        belongsToCollection = this@toUiModel.belongsToCollection?.toUiModel()
        budget = this@toUiModel.budget
        genres = this@toUiModel.genres?.toUiModel() ?: emptyList()
        homepage = this@toUiModel.homepage
        id = this@toUiModel.id
        imdbId = this@toUiModel.imdbId
        originalLanguage = this@toUiModel.originalLanguage
        originalTitle = this@toUiModel.originalTitle
        overview = this@toUiModel.overview
        popularity = this@toUiModel.popularity
        posterPath = this@toUiModel.posterPath
        productionCompanies = this@toUiModel.productionCompanies?.toUiModel()
        productionCountries = this@toUiModel.productionCountries?.toUiModel()
        releaseDate = this@toUiModel.releaseDate
        revenue = this@toUiModel.revenue
        runtime = this@toUiModel.runtime
        spokenLanguages = this@toUiModel.spokenLanguages?.toUiModel()
        status = this@toUiModel.status
        tagline = this@toUiModel.tagline
        title = this@toUiModel.title
        video = this@toUiModel.video
        voteAverage = this@toUiModel.voteAverage
        voteCount = this@toUiModel.voteCount
    }
