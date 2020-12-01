package com.endeline.domain.extensions

import com.endeline.data.models.Person
import com.endeline.domain.uimodels.PersonUiModel
import java.util.*

fun Person.toUiModel() = PersonUiModel(
    adult = this.adult ?: false,
    gender = this.gender ?: -1,
    id = this.id ?: -1,
    knownForDepartment = this.knownForDepartment ?: "",
    name = this.name ?: "",
    originalName = this.originalName ?: "",
    popularity = this.popularity ?: -1.0,
    profilePath = this.profilePath ?: "",
    castId = this.castId ?: -1,
    character = this.character ?: "",
    creditId = this.creditId ?: "",
    order = this.order ?: -1,
    alsoKnownAs = this.alsoKnownAs ?: emptyList(),
    biography = this.biography ?: "",
    birthday = this.birthday ?: Date(),
    deathday = this.deathday,
    homepage = this.homepage ?: "",
    imdbId = this.imdbId ?: "",
    placeOfBirth = this.placeOfBirth ?: ""
)

fun List<Person>.toUiModel() = this.map { it.toUiModel() }
