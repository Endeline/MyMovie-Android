package com.endeline.data.models

import com.google.gson.annotations.SerializedName

data class Credits(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("cast") val cast: List<Person>? = null,
    @SerializedName("crew") val crew: List<Person>? = null
) {
    data class Person(
        @SerializedName("adult") val adult: Boolean? = null,
        @SerializedName("gender") val gender: Int? = null,
        @SerializedName("id") val id: Int? = null,
        @SerializedName("known_for_department") val knownForDepartment: String? = null,
        @SerializedName("name") val name: String? = null,
        @SerializedName("original_name") val originalName: String? = null,
        @SerializedName("popularity") val popularity: Double? = null,
        @SerializedName("profile_path") val profilePath: String? = null,
        @SerializedName("cast_id") val castId: Int? = null,
        @SerializedName("character") val character: String? = null,
        @SerializedName("credit_id") val creditId: String? = null,
        @SerializedName("order") val order: Int? = null
    )
}
