package com.endeline.movielibrary.extensions

import androidx.fragment.app.Fragment
import com.endeline.movielibrary.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Fragment.showOkDialog(title: Int, message: Int) {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(getString(title))
        .setMessage(getString(message))
        .setPositiveButton(getString(R.string.ok)) { _, _ -> }
        .show()
}

fun Fragment.showDialogWithButtons(
    title: Int,
    message: Int,
    positiveButtonText: Int,
    neutralButtonText: Int,
    positiveButtonClick: () -> Unit,
    neutralButtonClick: () -> Unit
) {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(getString(title))
        .setMessage(getString(message))
        .setNegativeButton(getString(R.string.cancel)) { _, _ -> }
        .setNeutralButton(getText(neutralButtonText)) { _, _ -> neutralButtonClick() }
        .setPositiveButton(getString(positiveButtonText)) { _, _ -> positiveButtonClick() }
        .show()
}