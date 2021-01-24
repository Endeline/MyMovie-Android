package com.endeline.movielibrary.data.responses

import com.google.gson.annotations.SerializedName

data class VideoLinks(
	@SerializedName("id") val id: Int?,
	@SerializedName("results") val results: List<Link>?
) {
	data class Link(
		@SerializedName("id") val id: String?,
		@SerializedName("iso_639_1") val iso_639_1: String?,
		@SerializedName("iso_3166_1") val iso_3166_1: String?,
		@SerializedName("key") val key: String?,
		@SerializedName("name") val name: String?,
		@SerializedName("site") val site: String?,
		@SerializedName("size") val size: Int?,
		@SerializedName("type") val type: String?
	)
}
