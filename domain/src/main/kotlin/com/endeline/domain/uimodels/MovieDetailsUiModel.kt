package com.endeline.domain.uimodels

data class MovieDetailsUiModel(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var belongsToCollection: BelongsToCollectionUiModel? = null,
    var budget: Int? = null,
    var genres: List<GenresUiModel>? = null,
    var homepage: String? = null,
    var id: Int? = null,
    var imdbId: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Double? = null,
    var posterPath: String? = null,
    var productionCompanies: List<ProductionCompaniesUiModel>? = null,
    var productionCountries: List<ProductionCountriesUiModel>? = null,
    var releaseDate: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var spokenLanguages: List<SpokenLanguagesUiModel>? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Double? = null,
    var voteCount: Int? = null
)
