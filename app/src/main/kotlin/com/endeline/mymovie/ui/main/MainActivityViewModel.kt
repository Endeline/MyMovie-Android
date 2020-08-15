package com.endeline.mymovie.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.endeline.domain.usecase.GetUserIsLoggedInUseCase
import com.endeline.domain.usecase.InitializeUserServiceUseCase

class MainActivityViewModel(
    private val initializeUserServiceUseCase: InitializeUserServiceUseCase
) : ViewModel() {

    fun initializeServices(context: Context) = initializeUserServiceUseCase(context)
}