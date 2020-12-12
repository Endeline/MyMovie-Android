package com.endeline.domain.uimodels

data class PersonCreditsUiModel(
    val id: Int,
    val cast: List<PersonUiModel>,
    val crew: List<PersonUiModel>
)
