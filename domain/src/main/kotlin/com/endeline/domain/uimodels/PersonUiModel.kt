package com.endeline.domain.uimodels

import java.util.*

data class PersonUiModel(
    val birthday: Date,
    val knownForDepartment: String,
    val deathday: Date?,
    val id: Int,
    val name: String,
    val alsoKnownAs: List<String>,
    val gender: Int,
    val biography: String,
    val popularity: Double,
    val placeOfBirth: String,
    val profilePath: String,
    val adult: Boolean,
    val imdbId: String,
    val homepage: String,
    val originalName: String,
    val castId: Int,
    val character: String,
    val creditId: String,
    val order: Int
)
