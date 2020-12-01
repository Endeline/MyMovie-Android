package com.endeline.mymovie.ui.gui.credit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.uimodels.PersonUiModel
import com.endeline.domain.usecase.GetPersonDetailsUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PersonViewModel(val getPersonDetailsUseCase: GetPersonDetailsUseCase) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _personDetails = MutableLiveData<PersonUiModel>()

    val personDetails: LiveData<PersonUiModel>
        get() = _personDetails

    override fun onCleared() {
        super.onCleared()
        subscription.clear()
    }

    fun load(personId: Int) {
        loadGlobalData(personId)
    }

    private fun loadGlobalData(personId: Int) {
        val disposable = getPersonDetailsUseCase(personId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _personDetails.value = it
            }, Timber::e)

        subscription.add(disposable)
    }
}
