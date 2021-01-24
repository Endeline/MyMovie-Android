package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.PersonCreditsUiModel
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.data.responses.PersonCredits

fun PersonCredits.toUiModel() = PersonCreditsUiModel(
    id = id ?: EMPTY_VALUE,
    cast = cast?.toUiModel() ?: emptyList(),
    crew = crew?.toUiModel() ?: emptyList()
)
