package com.endeline.domain.extensions

import com.endeline.data.pojos.MovieLatestPojo
import com.endeline.domain.uimodels.MovieLatestUiModel

fun MovieLatestPojo.toUiModel(): MovieLatestUiModel =
    MovieLatestUiModel().apply {
        adult = this@toUiModel.adult
        backdropPath = this@toUiModel.backdropPath
        belongsToCollection = this@toUiModel.belongsToCollection
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
        productionCompanies = this@toUiModel.productionCompanies
//        productionCountries = this@toUiModel.productionCountries
        releaseDate = this@toUiModel.releaseDate
        revenue = this@toUiModel.revenue
        runtime = this@toUiModel.runtime
//        spokenLanguages = this@toUiModel.spokenLanguages
        status = this@toUiModel.status
        tagline = this@toUiModel.tagline
        title = this@toUiModel.title
        video = this@toUiModel.video
        voteAverage = this@toUiModel.voteAverage
        voteCount = this@toUiModel.voteCount
    }
