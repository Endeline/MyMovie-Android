package com.endeline.movielibrary.ui.extensions

import android.content.res.Resources

fun Int.px() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Int.dp() = this / Resources.getSystem().displayMetrics.density
