package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class KnownFor (
	@SerializedName("poster_path")
	val posterPath : String?,

	@SerializedName("vote_count")
	val voteCount : Int?,

	@SerializedName("video")
	val video : Boolean?,

	@SerializedName("media_type")
	val mediaType : String?,

	@SerializedName("id")
	val id : Int?,

	@SerializedName("adult")
	val adult : Boolean?,

	@SerializedName("backdrop_path")
	val backdropPath : String?,

	@SerializedName("original_language")
	val originalLanguage : String?,

	@SerializedName("original_title")
	val originalTitle : String?,

	@SerializedName("genre_ids")
	val genreIds : List<Int>?,

	@SerializedName("title")
	val title : String?,

	@SerializedName("vote_average")
	val voteAverage : Double?,

	@SerializedName("overview")
	val overview : String?,

	@SerializedName("release_date")
	val releaseDate : String?
)
