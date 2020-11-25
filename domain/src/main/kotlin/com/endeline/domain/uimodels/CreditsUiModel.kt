package com.endeline.domain.uimodels

data class CreditsUiModel(
    val id: Int,
    val cast: List<PersonUiModel>,
    val crew: List<PersonUiModel>
) {
    data class PersonUiModel(
        val adult: Boolean,
        val gender: Int,
        val id: Int,
        val knownForDepartment: String,
        val name: String,
        val originalName: String,
        val popularity: Double,
        val profilePath: String,
        val castId: Int,
        val character: String,
        val creditId: String,
        val order: Int
    )
}
