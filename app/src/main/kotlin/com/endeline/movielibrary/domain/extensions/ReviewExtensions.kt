package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.ReviewsUiModel
import com.endeline.movielibrary.common.Constants.DEFAULT_DATE
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.Reviews

fun Reviews.Author.toUiModel() = ReviewsUiModel.AuthorUiModel(
    name = name ?: EMPTY_TEXT,
    userName = userName ?: EMPTY_TEXT,
    avatarPath = avatarPath ?: EMPTY_TEXT,
    rating = rating ?: EMPTY_VALUE
)

fun Reviews.Review.toUiModel() = ReviewsUiModel.ReviewUiModel(
    id = id ?: EMPTY_TEXT,
    author = author ?: EMPTY_TEXT,
    content = content ?: EMPTY_TEXT,
    iso_639_1 = iso_639_1 ?: EMPTY_TEXT,
    mediaId = mediaId ?: EMPTY_VALUE,
    mediaTitle = mediaTitle ?: EMPTY_TEXT,
    mediaType = mediaType ?: EMPTY_TEXT,
    url = url ?: EMPTY_TEXT,
    authorDetails = authorDetails?.toUiModel() ?: ReviewsUiModel.AuthorUiModel.EMPTY,
    createdAt = createdAt ?: DEFAULT_DATE,
    updatedAt = updatedAt ?: DEFAULT_DATE
)

fun List<Reviews.Review>.toUiModel() = map { it.toUiModel() }

fun Reviews.toUiModel() = ReviewsUiModel(
    page = page ?: EMPTY_VALUE,
    id = id ?: EMPTY_VALUE,
    result = result?.toUiModel() ?: emptyList(),
    totalPages = totalPages ?: EMPTY_VALUE,
    totalResult = totalResult ?: EMPTY_VALUE
)
