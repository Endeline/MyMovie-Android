package com.endeline.movielibrary.ui.gui.splash

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.movielibrary.domain.usecase.LoadAllDataUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val loadAllDataUseCase: LoadAllDataUseCase) : BaseViewModel() {

    private val _dataLoaded = MutableLiveData<Boolean>()

    val dataLoaded: LiveData<Boolean>
        get() = _dataLoaded

    init {
        loadData()
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
