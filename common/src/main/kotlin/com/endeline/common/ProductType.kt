package com.endeline.common

enum class ProductType(val type: String) {
    MOVIE("movie"),
    TV("tv"),
    PERSON("person"),
    OTHER("");

    companion object {
        fun fromString(name: String?) =
            values().first {
                it.type == name
            }
    }
}
