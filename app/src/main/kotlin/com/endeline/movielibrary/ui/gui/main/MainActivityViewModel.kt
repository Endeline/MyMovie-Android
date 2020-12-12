package com.endeline.movielibrary.ui.gui.main

import android.content.Context
import com.endeline.domain.usecase.InitializeUserServiceUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel

class MainActivityViewModel(
    private val initializeUserServiceUseCase: InitializeUserServiceUseCase
) : BaseViewModel() {

    fun initializeServices(context: Context) = initializeUserServiceUseCase(context)
}