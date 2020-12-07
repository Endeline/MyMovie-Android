package com.endeline.movielibrary.ui.gui.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.LoadAllDataUseCase
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(private val loadAllDataUseCase: LoadAllDataUseCase) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _dataLoaded = MutableLiveData<Boolean>()

    val dataLoaded: LiveData<Boolean>
        get() = _dataLoaded

    init {
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    //TODO change this to load only home data not all
    fun loadData() {
        Handler().postDelayed({
            _dataLoaded.value = true
        }, 2000)

//        val disposable = loadAllDataUseCase()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                _dataLoadedLiveData.value = true
//            }, {
//                Timber.e(it)
//                _dataLoadedLiveData.value = false
//            })
//
//        subscriptions.add(disposable)
    }
}
