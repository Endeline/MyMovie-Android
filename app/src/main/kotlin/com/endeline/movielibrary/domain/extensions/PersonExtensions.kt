package com.endeline.movielibrary.domain.extensions

import com.endeline.movielibrary.domain.uimodels.PersonUiModel
import com.endeline.movielibrary.common.Constants.DEFAULT_DATE
import com.endeline.movielibrary.common.Constants.EMPTY_TEXT
import com.endeline.movielibrary.common.Constants.EMPTY_VALUE
import com.endeline.movielibrary.common.Constants.NO_VALUE
import com.endeline.movielibrary.data.responses.Person

fun Person.toUiModel() = PersonUiModel(
    adult = adult ?: false,
    gender = gender ?: EMPTY_VALUE,
    id = id ?: EMPTY_VALUE,
    knownForDepartment = knownForDepartment ?: EMPTY_TEXT,
    name = name ?: EMPTY_TEXT,
    originalName = originalName ?: EMPTY_TEXT,
    popularity = popularity ?: NO_VALUE,
    profilePath = profilePath ?: EMPTY_TEXT,
    castId = castId ?: EMPTY_VALUE,
    character = character ?: EMPTY_TEXT,
    creditId = creditId ?: EMPTY_TEXT,
    order = order ?: EMPTY_VALUE,
    alsoKnownAs = alsoKnownAs ?: emptyList(),
    biography = biography ?: EMPTY_TEXT,
    birthday = birthday ?: DEFAULT_DATE,
    deathday = deathday,
    homepage = homepage ?: EMPTY_TEXT,
    imdbId = imdbId ?: EMPTY_TEXT,
    placeOfBirth = placeOfBirth ?: EMPTY_TEXT
)

fun List<Person>.toUiModel() = map { it.toUiModel() }
