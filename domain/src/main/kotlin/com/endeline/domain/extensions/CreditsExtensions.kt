package com.endeline.domain.extensions

import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.data.models.Credits
import com.endeline.domain.uimodels.CreditsUiModel

fun Credits.toUiModel() = CreditsUiModel(
    id = this.id ?: EMPTY_VALUE,
    cast = this.cast?.toUiModel() ?: emptyList(),
    crew = this.crew?.toUiModel() ?: emptyList()
)
