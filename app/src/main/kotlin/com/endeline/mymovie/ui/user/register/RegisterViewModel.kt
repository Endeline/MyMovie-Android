package com.endeline.mymovie.ui.user.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.CheckExistLoginUseCase
import com.endeline.domain.usecase.RegisterUseCase
import timber.log.Timber

class RegisterViewModel(
    private val checkExistLoginUseCase: CheckExistLoginUseCase,
    private val registerUseCase: RegisterUseCase
) : ViewModel() {

    private val registerStatusLiveData = MutableLiveData<RegisterStatus>()

    fun getRegisterStatusLiveData(): LiveData<RegisterStatus> = registerStatusLiveData

    fun registerUser(login: String, password: String) = checkExistLoginUseCase(login)
        .subscribe({
            if (it) {
                registerStatusLiveData.postValue(RegisterStatus.USER_ALREADY_EXIST)
            } else {
                registerUseCase(login, password)
                    .subscribe({
                        registerStatusLiveData.postValue(
                            if (it.id != null) {
                                RegisterStatus.OK
                            } else {
                                RegisterStatus.FAILED
                            }
                        )
                    }, this::registerPostFailed)
                    .dispose()
            }
        }, this::registerPostFailed)
        .dispose()

    private fun registerPostFailed(error: Throwable) {
        Timber.e(error)
        registerStatusLiveData.postValue(RegisterStatus.FAILED)
    }
}
