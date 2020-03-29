package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class VideoLinkCollection (
	@SerializedName("id")
	val id : Int? = null,

	@SerializedName("results")
	val results : List<VideoLinkDetails>? = null
)
