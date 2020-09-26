package com.endeline.mymovie.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.LoadAllDataUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SplashViewModel(private val loadAllDataUseCase: LoadAllDataUseCase) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private val _dataLoadedLiveData = MutableLiveData<Boolean>()

    val dataLoadedLiveData: LiveData<Boolean>
        get() = _dataLoadedLiveData

    init {
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    //TODO change this to load only home data not all
    fun loadData() {

        _dataLoadedLiveData.value = true

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
