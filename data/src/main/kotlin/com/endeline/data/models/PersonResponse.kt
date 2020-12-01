package com.endeline.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Person(
    @SerializedName("birthday") val birthday: Date? = null,
    @SerializedName("known_for_department") val knownForDepartment: String? = null,
    @SerializedName("deathday") val deathday: Date? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("also_known_as") val alsoKnownAs: List<String>? = null,
    @SerializedName("gender") val gender: Int? = null,
    @SerializedName("biography") val biography: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("place_of_birth") val placeOfBirth: String? = null,
    @SerializedName("profile_path") val profilePath: String? = null,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("imdb_id") val imdbId: String? = null,
    @SerializedName("homepage") val homepage: String? = null,
    @SerializedName("original_name") val originalName: String? = null,
    @SerializedName("cast_id") val castId: Int? = null,
    @SerializedName("character") val character: String? = null,
    @SerializedName("credit_id") val creditId: String? = null,
    @SerializedName("order") val order: Int? = null
)
