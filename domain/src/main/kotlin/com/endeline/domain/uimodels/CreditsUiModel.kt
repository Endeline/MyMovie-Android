package com.endeline.domain.uimodels

data class CreditsUiModel(
    val id: Int,
    val cast: List<PersonUiModel>,
    val crew: List<PersonUiModel>
)
