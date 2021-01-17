package com.endeline.movielibrary

object Constants {

    object Duration {
        const val RECYCLER_VIEW_ITEM_DURATION = 250L
        const val DEFAULT_SPEED = 50F
        const val AUTO_SCROLLING_PERIOD_TIME = 5000L
        const val AUTO_SCROLLING_DELAY_TIME = 2000L

        const val TIMEOUT_IN_MS = 60 * 1000
    }

    object Size {
        const val WINDOW_WIDTH = 300
        const val WINDOW_HEIGHT = 400

        const val DEFAULT_POSTER_IMAGE_HEIGHT = 200
        const val DEFAULT_POSTER_IMAGE_WIDTH = 150

        const val SMALL_POSTER_IMAGE_HEIGHT = 150
        const val SMALL_POSTER_IMAGE_WIDTH = 100
    }

    object Image {
        const val BACKDROP_URL = "https://image.tmdb.org/t/p/w500"
        const val POSTER_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"
        const val HTTPS_URL = "https"
        const val SLASH = "/"

        const val SUB_SEQUENCE_VALUE = 1
    }

    object Values {
        const val VALUE_ZERO = 0
        const val VALUE_ONE = 1
    }

    object Collections {
        const val MINIMUM_COLLECTION_SIZE = 1
    }

    object String {
        const val MINIMUM_CHARACTER_AMOUNT = 6
        const val MINIMUM_TEXT_SIZE_TO_SEARCH = 3

        const val UNSUPPORTED_TYPE = "Unsupported type"
    }

    object Position {
        const val FIRST = 0
        const val SECOND = 1
        const val THIRD = 2
        const val FOURTH = 3
    }

    object Date {
        const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy"
    }
}
