package com.endeline.movielibrary.ui.gui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.domain.usecase.CheckIsUserInAppUseCase
import com.endeline.domain.usecase.GetUserIsLoggedInUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber

class UserViewModel(
    private val getUserIsLoggedInUseCase: GetUserIsLoggedInUseCase,
    private val getUserInAppUseCase: CheckIsUserInAppUseCase
) : BaseViewModel() {

    private var _changeLoginStatus = MutableLiveData<Boolean>()

    val changeLoginStatus: LiveData<Boolean>
        get() = _changeLoginStatus

    fun isUserLogged(): Boolean = getUserIsLoggedInUseCase().blockingGet()

    fun login(login: String, password: String) = subscription.add(
        getUserInAppUseCase(login, password)
            .subscribe({
                _changeLoginStatus.value = it
            }, Timber::e)
    )
}
