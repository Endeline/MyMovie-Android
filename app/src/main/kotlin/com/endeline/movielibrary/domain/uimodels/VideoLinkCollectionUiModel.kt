package com.endeline.movielibrary.domain.uimodels

data class VideoLinkCollectionUiModel(
	val id: Int,
	val results: List<VideoLinkDetailsUiModel>
) {
    data class VideoLinkDetailsUiModel(
		val id: String,
		val iso_639_1: String,
		val iso_3166_1: String,
		val key: String,
		val name: String,
		val site: String,
		val size: Int,
		val type: String
	)
}
