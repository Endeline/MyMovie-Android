package com.endeline.domain.uimodels

data class KnownForUiModel (
	var posterPath : String? = null,
	var voteCount : Int? = null,
	var video : Boolean? = null,
	var mediaType : String? = null,
	var id : Int? = null,
	var adult : Boolean? = null,
	var backdropPath : String? = null,
	var originalLanguage : String? = null,
	var originalTitle : String? = null,
	var genreIds : List<Int>? = null,
	var title : String? = null,
	var voteAverage : Double? = null,
	var overview : String? = null,
	var releaseDate : String? = null
)
