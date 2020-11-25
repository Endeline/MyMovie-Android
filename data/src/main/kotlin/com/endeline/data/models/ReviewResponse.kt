package com.endeline.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Reviews(
    @SerializedName("id") val id: Int?,
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val result: List<Review>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResult: Int?
) {
    data class Review(
        @SerializedName("id") val id: String?,
        @SerializedName("author") val author: String?,
        @SerializedName("content") val content: String?,
        @SerializedName("iso_639_1") val iso_639_1: String?,
        @SerializedName("media_id") val mediaId: Int?,
        @SerializedName("media_title") val mediaTitle: String?,
        @SerializedName("media_type") val mediaType: String?,
        @SerializedName("url") val url: String?,
        @SerializedName("author_details") val authorDetails: Author?,
        @SerializedName("created_at") val createdAt: Date?,
        @SerializedName("updated_at") val updatedAt: Date?
    )

    data class Author(
        @SerializedName("name") val name: String?,
        @SerializedName("username") val userName: String?,
        @SerializedName("avatar_path") val avatarPath: String?,
        @SerializedName("rating") val rating: Int?
    )
}
