package com.endeline.domain.extensions

import com.endeline.common.Constants.DEFAULT_DATE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.responses.Reviews
import com.endeline.domain.uimodels.ReviewsUiModel

fun Reviews.Author.toUiModel() = ReviewsUiModel.AuthorUiModel(
    name = this.name ?: EMPTY_TEXT,
    userName = this.userName ?: EMPTY_TEXT,
    avatarPath = this.avatarPath ?: EMPTY_TEXT,
    rating = this.rating ?: EMPTY_VALUE
)

fun Reviews.Review.toUiModel() = ReviewsUiModel.ReviewUiModel(
    id = this.id ?: EMPTY_TEXT,
    author = this.author ?: EMPTY_TEXT,
    content = this.content ?: EMPTY_TEXT,
    iso_639_1 = this.iso_639_1 ?: EMPTY_TEXT,
    mediaId = this.mediaId ?: EMPTY_VALUE,
    mediaTitle = this.mediaTitle ?: EMPTY_TEXT,
    mediaType = this.mediaType ?: EMPTY_TEXT,
    url = this.url ?: EMPTY_TEXT,
    authorDetails = this.authorDetails?.toUiModel() ?: ReviewsUiModel.AuthorUiModel.EMPTY,
    createdAt = this.createdAt ?: DEFAULT_DATE,
    updatedAt = this.updatedAt ?: DEFAULT_DATE
)

fun List<Reviews.Review>.toUiModel() = this.map { it.toUiModel() }

fun Reviews.toUiModel() = ReviewsUiModel(
    page = this.page ?: EMPTY_VALUE,
    id = this.id ?: EMPTY_VALUE,
    result = this.result?.toUiModel() ?: emptyList(),
    totalPages = this.totalPages ?: EMPTY_VALUE,
    totalResult = this.totalResult ?: EMPTY_VALUE
)
