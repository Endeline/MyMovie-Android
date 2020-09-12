package com.endeline.mymovie.extensions

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

inline fun <T : Any> ifNotEmpty(element: T, closure: (T) -> Unit) {
    if (element is Double && element as Double != -1.0) {
        closure(element)
    }

    if (element is String && element.isNotBlank()) {
        closure(element)
    }

    if (element is List<*> && element.isNotEmpty()) {
        closure(element)
    }
}
