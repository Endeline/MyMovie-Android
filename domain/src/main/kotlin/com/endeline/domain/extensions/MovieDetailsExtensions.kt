package com.endeline.domain.extensions

import com.endeline.common.Constants.DEFAULT_DATE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.models.ProductDetails
import com.endeline.data.models.ProductDetails.BelongsToCollection
import com.endeline.domain.uimodels.ProductDetailsUiModel
import java.util.*

fun ProductDetails.toUiModel() = ProductDetailsUiModel(
    adult = this@toUiModel.adult ?: false,
    backdropPath = this@toUiModel.backdropPath ?: EMPTY_TEXT,
    budget = this@toUiModel.budget ?: EMPTY_VALUE,
    genres = this@toUiModel.genres?.toUiModel() ?: emptyList(),
    homepage = this@toUiModel.homepage ?: EMPTY_TEXT,
    id = this@toUiModel.id ?: EMPTY_VALUE,
    imdbId = this@toUiModel.imdbId ?: EMPTY_TEXT,
    originalLanguage = this@toUiModel.originalLanguage ?: EMPTY_TEXT,
    originalTitle = this@toUiModel.originalTitle ?: EMPTY_TEXT,
    overview = this@toUiModel.overview ?: EMPTY_TEXT,
    popularity = this@toUiModel.popularity ?: NO_VALUE,
    posterPath = this@toUiModel.posterPath ?: EMPTY_TEXT,
    productionCountries = this@toUiModel.productionCountries?.toUiModel() ?: emptyList(),
    releaseDate = this@toUiModel.releaseDate ?: DEFAULT_DATE,
    revenue = this@toUiModel.revenue ?: EMPTY_VALUE,
    runtime = this@toUiModel.runtime ?: EMPTY_VALUE,
    spokenLanguages = this@toUiModel.spokenLanguages?.toUiModel() ?: emptyList(),
    status = this@toUiModel.status ?: EMPTY_TEXT,
    tagline = this@toUiModel.tagline ?: EMPTY_TEXT,
    title = this@toUiModel.title ?: EMPTY_TEXT,
    video = this@toUiModel.video ?: false,
    voteAverage = this@toUiModel.voteAverage ?: NO_VALUE,
    voteCount = this@toUiModel.voteCount ?: EMPTY_VALUE,
    productionCompanies = this@toUiModel.productionCompanies?.toUiModel() ?: emptyList(),
    belongsToCollection = this@toUiModel.belongsToCollection?.toUiModel()
        ?: BelongsToCollection.EMPTY.toUiModel()
)
