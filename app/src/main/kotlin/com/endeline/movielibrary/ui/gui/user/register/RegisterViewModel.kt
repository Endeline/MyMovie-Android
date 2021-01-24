package com.endeline.movielibrary.ui.gui.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.movielibrary.domain.usecase.CheckExistLoginUseCase
import com.endeline.movielibrary.domain.usecase.RegisterUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val checkExistLoginUseCase: CheckExistLoginUseCase,
    private val registerUseCase: RegisterUseCase
) : BaseViewModel() {

    private val _registerStatus = MutableLiveData<RegisterStatus>()

    val registerStatus: LiveData<RegisterStatus>
        get() = _registerStatus

    fun registerUser(login: String, password: String) = subscription.add(
        checkExistLoginUseCase(login)
            .subscribe({ userExist ->
                if (userExist) {
                    _registerStatus.value = RegisterStatus.USER_ALREADY_EXIST
                } else {
                    register(login, password)
                }
            }, this::registerPostFailed)
    )

    private fun register(login: String, password: String) = subscription.add(
        registerUseCase(login, password)
            .subscribe({ user ->
                _registerStatus.postValue(
                    if (user.id != null) {
                        RegisterStatus.OK
                    } else {
                        RegisterStatus.FAILED
                    }
                )
            }, this::registerPostFailed)
    )

    private fun registerPostFailed(error: Throwable) {
        Timber.e(error)
        _registerStatus.postValue(RegisterStatus.FAILED)
    }
}
