package com.endeline.domain.extensions

import com.endeline.common.Constants.DEFAULT_DATE
import com.endeline.common.Constants.EMPTY_TEXT
import com.endeline.common.Constants.EMPTY_VALUE
import com.endeline.common.Constants.NO_VALUE
import com.endeline.data.models.Person
import com.endeline.domain.uimodels.PersonUiModel

fun Person.toUiModel() = PersonUiModel(
    adult = this.adult ?: false,
    gender = this.gender ?: EMPTY_VALUE,
    id = this.id ?: EMPTY_VALUE,
    knownForDepartment = this.knownForDepartment ?: EMPTY_TEXT,
    name = this.name ?: EMPTY_TEXT,
    originalName = this.originalName ?: EMPTY_TEXT,
    popularity = this.popularity ?: NO_VALUE,
    profilePath = this.profilePath ?: EMPTY_TEXT,
    castId = this.castId ?: EMPTY_VALUE,
    character = this.character ?: EMPTY_TEXT,
    creditId = this.creditId ?: EMPTY_TEXT,
    order = this.order ?: EMPTY_VALUE,
    alsoKnownAs = this.alsoKnownAs ?: emptyList(),
    biography = this.biography ?: EMPTY_TEXT,
    birthday = this.birthday ?: DEFAULT_DATE,
    deathday = this.deathday,
    homepage = this.homepage ?: EMPTY_TEXT,
    imdbId = this.imdbId ?: EMPTY_TEXT,
    placeOfBirth = this.placeOfBirth ?: EMPTY_TEXT
)

fun List<Person>.toUiModel() = this.map { it.toUiModel() }
