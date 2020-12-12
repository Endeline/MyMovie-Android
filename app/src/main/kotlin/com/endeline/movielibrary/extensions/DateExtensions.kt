package com.endeline.movielibrary.extensions

import com.endeline.movielibrary.Constants.Date.SIMPLE_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

private val formatter = SimpleDateFormat(SIMPLE_DATE_FORMAT, Locale.ENGLISH)

fun Date.toSimpleDate() = formatter.format(this)
