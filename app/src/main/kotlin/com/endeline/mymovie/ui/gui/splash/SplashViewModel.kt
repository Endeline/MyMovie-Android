package com.endeline.mymovie.ui.gui.splash

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

        _dataLoaded.value = true

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
