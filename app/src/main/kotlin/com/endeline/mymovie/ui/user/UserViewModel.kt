package com.endeline.mymovie.ui.user

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.CheckIsUserInAppUseCase
import com.endeline.domain.usecase.GetUserIsLoggedInUseCase
import timber.log.Timber

class UserViewModel(
     private val getUserIsLoggedInUseCase: GetUserIsLoggedInUseCase,
     private val getUserInAppUseCase: CheckIsUserInAppUseCase
) : ViewModel() {

    private var changeLoginStatusLiveData = MutableLiveData<Boolean>()

    fun getChangeLoginStatusLiveData(): LiveData<Boolean> = changeLoginStatusLiveData

    fun isUserLogged(): Boolean = getUserIsLoggedInUseCase().blockingGet()

    @SuppressLint("CheckResult")
    fun login(login: String, password: String) {
        getUserInAppUseCase(login, password)
            .subscribe({
                changeLoginStatusLiveData.postValue(it)
            }, Timber::e)
    }

}
