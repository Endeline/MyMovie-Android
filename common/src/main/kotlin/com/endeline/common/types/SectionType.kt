package com.endeline.common.types

enum class SectionType(val type: String) {
    POPULAR("popular"),
    TOP_RATED("top_rated"),
    NOW_PLAYING("now_playing"),
    UPCOMING("upcoming"),
    AIRING_TODAY("airing_today"),
    ON_THE_AIR("on_the_air"),

    SIMILAR("similar"),
    RECOMMENDATIONS("recommendations"),

    VIDEO("videos"),
    IMAGES("images"),
    REVIEWS("reviews"),
    CREDITS("credits"),

    TV_CREDITS("tv_credits"),
    MOVIE_CREDITS("movie_credits"),

    NONE("")
}