package com.endeline.domain.uimodels

data class SearchItemUiModel (
	var posterPath : String? = null,
	var popularity : Double? = null,
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
	var releaseDate : String? = null,



	var knownForDepartment: String? = null,
	var name: String? = null,
	var knownFor: List<KnownForUiModel>? = null,
	var profilePath: String? = null,
	var gender: Int? = null
)
