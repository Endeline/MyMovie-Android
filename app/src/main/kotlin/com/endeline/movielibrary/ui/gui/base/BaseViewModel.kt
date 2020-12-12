package com.endeline.movielibrary.ui.gui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    protected val subscription = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }
}