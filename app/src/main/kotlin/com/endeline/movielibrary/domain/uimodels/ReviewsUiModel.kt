package com.endeline.movielibrary.domain.uimodels

import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import java.util.*

data class ReviewsUiModel(
    val id: Int,
    val page: Int,
    val result: List<ReviewUiModel>,
    val totalPages: Int,
    val totalResult: Int
) {
    data class ReviewUiModel(
        val id: String,
        val author: String,
        val content: String,
        val iso_639_1: String,
        val mediaId: Int,
        val mediaTitle: String,
        val mediaType: String,
        val url: String,
        val authorDetails: AuthorUiModel,
        val createdAt: Date,
        val updatedAt: Date
    )

    data class AuthorUiModel(
        val name: String,
        val userName: String,
        val avatarPath: String,
        val rating: Int
    ) {
        companion object {
            val EMPTY = AuthorUiModel(
                name = EMPTY_TEXT,
                userName = EMPTY_TEXT,
                avatarPath = EMPTY_TEXT,
                rating = EMPTY_VALUE
            )
        }
    }
}
