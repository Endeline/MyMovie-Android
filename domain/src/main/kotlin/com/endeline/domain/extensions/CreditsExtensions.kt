package com.endeline.domain.extensions

import com.endeline.data.models.Credits
import com.endeline.domain.uimodels.CreditsUiModel

fun Credits.toUiModel() = CreditsUiModel(
    id = this.id ?: -1,
    cast = this.cast?.toUiModel() ?: emptyList(),
    crew = this.crew?.toUiModel() ?: emptyList()
)
