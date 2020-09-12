package com.endeline.mymovie.ui.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.CheckExistLoginUseCase
import com.endeline.domain.usecase.RegisterUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RegisterViewModel(
    private val checkExistLoginUseCase: CheckExistLoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val subscription = CompositeDisposable()

    private val _registerStatusLiveData = MutableLiveData<RegisterStatus>()

    val registerStatusLiveData: LiveData<RegisterStatus>
        get() = _registerStatusLiveData

    fun registerUser(login: String, password: String) {
        val disposable = checkExistLoginUseCase(login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userExist ->
                if (userExist) {
                    _registerStatusLiveData.value = RegisterStatus.USER_ALREADY_EXIST
                } else {
                    register(login, password)
                }
            }, this::registerPostFailed)

        subscription.add(disposable)
    }

    private fun register(login: String, password: String) {
        val disposable = registerUseCase(login, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                _registerStatusLiveData.postValue(
                    if (user.id != null) {
                        RegisterStatus.OK
                    } else {
                        RegisterStatus.FAILED
                    }
                )
            }, this::registerPostFailed)

        subscription.add(disposable)
    }

    private fun registerPostFailed(error: Throwable) {
        Timber.e(error)
        _registerStatusLiveData.postValue(RegisterStatus.FAILED)
    }
}
