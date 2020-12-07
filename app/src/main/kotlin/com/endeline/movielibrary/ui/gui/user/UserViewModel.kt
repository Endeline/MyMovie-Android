package com.endeline.movielibrary.ui.gui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.CheckIsUserInAppUseCase
import com.endeline.domain.usecase.GetUserIsLoggedInUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UserViewModel(
    private val getUserIsLoggedInUseCase: GetUserIsLoggedInUseCase,
    private val getUserInAppUseCase: CheckIsUserInAppUseCase
) : ViewModel() {

    private val subscriptions = CompositeDisposable()

    private var _changeLoginStatus = MutableLiveData<Boolean>()

    val changeLoginStatus: LiveData<Boolean>
        get() = _changeLoginStatus

    fun isUserLogged(): Boolean = getUserIsLoggedInUseCase().blockingGet()

    fun login(login: String, password: String) {
        val disposable = getUserInAppUseCase(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _changeLoginStatus.value = it
            }, Timber::e)

        subscriptions.add(disposable)
    }

}
