package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.models.PersonCredits
import com.endeline.domain.uimodels.PersonCreditsUiModel

fun PersonCredits.toUiModel() = PersonCreditsUiModel(
    id = this.id ?: EMPTY_VALUE,
    cast = this.cast?.toUiModel() ?: emptyList(),
    crew = this.crew?.toUiModel() ?: emptyList()
)
