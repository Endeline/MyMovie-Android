package com.endeline.domain.extensions

import com.endeline.data.models.Reviews
import com.endeline.domain.uimodels.ReviewsUiModel
import java.util.*

fun Reviews.Author.toUiModel() = ReviewsUiModel.AuthorUiModel(
    name = this.name ?: "",
    userName = this.userName ?: "",
    avatarPath = this.avatarPath ?: "",
    rating = this.rating ?: -1
)

fun Reviews.Review.toUiModel() = ReviewsUiModel.ReviewUiModel(
    id = this.id ?: "",
    author = this.author ?: "",
    content = this.content ?: "",
    iso_639_1 = this.iso_639_1 ?: "",
    mediaId = this.mediaId ?: -1,
    mediaTitle = this.mediaTitle ?: "",
    mediaType = this.mediaType ?: "",
    url = this.url ?: "",
    authorDetails = this.authorDetails?.toUiModel() ?: ReviewsUiModel.AuthorUiModel.EMPTY,
    createdAt = this.createdAt ?: Date(),
    updatedAt = this.updatedAt ?: Date()
)

fun List<Reviews.Review>.toUiModel() = this.map { it.toUiModel() }

fun Reviews.toUiModel() = ReviewsUiModel(
    page = this.page ?: -1,
    id = this.id ?: -1,
    result = this.result?.toUiModel() ?: emptyList(),
    totalPages = this.totalPages ?: -1,
    totalResult = this.totalResult ?: -1
)
