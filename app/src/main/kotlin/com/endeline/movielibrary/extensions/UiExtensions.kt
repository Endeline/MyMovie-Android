package com.endeline.movielibrary.extensions

import android.view.View
import android.widget.TextView
import androidx.annotation.IntDef
import androidx.appcompat.widget.LinearLayoutCompat
import com.endeline.common.Constants.NO_VALUE

@IntDef(View.VISIBLE, View.INVISIBLE, View.GONE)
annotation class Visibility

inline fun <T : Any> ifLet(vararg elements: T?, closure: (List<T>) -> Unit) {
    if (elements.all { it != null }) {
        closure(elements.filterNotNull())
    }
}

inline fun <T : Any> ifNotEmpty(element: T, closure: (T) -> Unit) {
    if (element is Double && element as Double != NO_VALUE) {
        closure(element)
        return
    }

    if (element is String && element.isNotBlank() && element.isNotEmpty()) {
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

fun onDataLoaded(title: View, container: LinearLayoutCompat, texts: List<String>) {
    title.visibility = View.VISIBLE

    texts.forEach {
        container.addView(TextView(container.context).apply {
            text = it
        })
    }
}

fun <T : Any> onDataLoaded(
    items: List<T>,
    adapter: androidx.recyclerview.widget.ListAdapter<T, *>,
    vararg views: View
) {
    ifNotEmpty(items) {
        setViewsVisibility(View.VISIBLE, *views)
        adapter.submitList(it)
    }
}