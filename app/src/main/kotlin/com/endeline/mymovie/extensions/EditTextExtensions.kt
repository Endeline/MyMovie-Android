package com.endeline.mymovie.extensions

import androidx.appcompat.widget.AppCompatEditText

private const val MINIMUM_CHARACTER_AMOUNT = 6

fun AppCompatEditText.validate(emptyError: String, toShortError: String) : Boolean{
    var result = true

    if (text.isNullOrEmpty()) {
        result = false
        error = emptyError
    }

    if (text.toString().length < MINIMUM_CHARACTER_AMOUNT) {
        result = false
        error = toShortError
    }

    if (!text.isNullOrEmpty() && text.toString().length >= MINIMUM_CHARACTER_AMOUNT) {
        error = null
    }

    return result
}