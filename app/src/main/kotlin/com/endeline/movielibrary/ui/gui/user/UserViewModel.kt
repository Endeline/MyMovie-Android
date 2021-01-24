package com.endeline.movielibrary.ui.gui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.endeline.movielibrary.domain.usecase.CheckIsUserInAppUseCase
import com.endeline.movielibrary.domain.usecase.GetUserIsLoggedInUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import timber.log.Timber
import javax.inject.Inject

class UserViewModel @Inject constructor(
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
