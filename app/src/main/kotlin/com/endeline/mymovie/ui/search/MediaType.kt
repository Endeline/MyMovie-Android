package com.endeline.mymovie.ui.search

enum class MediaType {
    person,
    movie,
    tv;

    companion object {
        fun fromString(name: String?) =
            values().first {
                it.toString() == name
            }
    }

}
