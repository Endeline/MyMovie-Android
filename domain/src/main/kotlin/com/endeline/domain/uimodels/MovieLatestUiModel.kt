package com.endeline.domain.uimodels

data class MovieLatestUiModel(
    var adult: Boolean? = null,
    var backdropPath: String? = null,
    var belongsToCollection: String? = null,
    var budget: Int? = null,
    var genres: List<GenresUiModel>? = null,
    var homepage: String? = null,
    var id: Int? = null,
    var imdbId: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Int? = null,
    var posterPath: String? = null,
//    var productionCompanies: List<String>? = null,
//    var productionCountries: List<String>? = null,
    var releaseDate: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
//    var spokenLanguages: List<String>? = null,
    var status: String? = null,
    var tagline: String? = null,
    var title: String? = null,
    var video: Boolean? = null,
    var voteAverage: Int? = null,
    var voteCount: Int? = null
)
