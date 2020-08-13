package com.endeline.mymovie.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetUserIsLoggedInUseCase
import com.endeline.domain.usecase.InitializeUserServiceUseCase

class MainActivityViewModel(
    val getUserIsLoggedInUseCase: GetUserIsLoggedInUseCase,
    val initializeUserServiceUseCase: InitializeUserServiceUseCase
) : ViewModel() {

    fun getUserIsLoggedIn() = getUserIsLoggedInUseCase()

    fun initializeServices(context: Context) = initializeUserServiceUseCase(context)
}