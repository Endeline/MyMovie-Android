package com.endeline.movielibrary.ui.extensions

import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import com.endeline.movielibrary.Constants.String.MINIMUM_CHARACTER_AMOUNT

fun AppCompatEditText.isValidInput(emptyError: String, toShortError: String): Boolean {
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

fun isSameEditText(firstEditText: EditText, secondEditText: EditText, errorText: String): Boolean {
    return if (firstEditText.text.toString() == secondEditText.text.toString()) {
        true
    } else {
        secondEditText.error = errorText
        false
    }
}