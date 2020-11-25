package com.endeline.domain.extensions

import com.endeline.data.models.Credits
import com.endeline.data.models.Credits.Person
import com.endeline.domain.uimodels.CreditsUiModel
import com.endeline.domain.uimodels.CreditsUiModel.PersonUiModel

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
    order = this.order ?: -1
)

fun List<Person>.toUiModel() = this.map { it.toUiModel() }

fun Credits.toUiModel() = CreditsUiModel(
    id = this.id ?: -1,
    cast = this.cast?.toUiModel() ?: emptyList(),
    crew = this.crew?.toUiModel() ?: emptyList()
)
