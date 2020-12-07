package com.endeline.movielibrary.extensions

import android.view.View
import androidx.annotation.IntDef

@IntDef(View.VISIBLE, View.INVISIBLE, View.GONE)
annotation class Visibility

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

inline fun <T : Any> ifNotEmpty(element: T, closure: (T) -> Unit) {
    if (element is Double && element as Double != -1.0) {
        closure(element)
        return
    }

    if (element is String && element.isNotBlank()) {
        closure(element)
        return
    }

    if (element is List<*> && element.isNotEmpty()) {
        closure(element)
        return
    }
}

fun setViewsVisibility(@Visibility visibility: Int, vararg views: View) {
    views.forEach { view ->
        view.visibility = visibility
    }
}
