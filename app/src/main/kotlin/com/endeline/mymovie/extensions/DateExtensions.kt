package com.endeline.mymovie.extensions

import java.text.SimpleDateFormat
import java.util.*

private val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)

fun Date.toSimpleDate() = formatter.format(this)
