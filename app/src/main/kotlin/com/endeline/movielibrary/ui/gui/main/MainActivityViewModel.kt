package com.endeline.movielibrary.ui.gui.main

import android.content.Context
import com.endeline.movielibrary.domain.usecase.InitializeUserServiceUseCase
import com.endeline.movielibrary.ui.gui.base.BaseViewModel
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val initializeUserServiceUseCase: InitializeUserServiceUseCase
) : BaseViewModel() {

    fun initializeServices(context: Context) = initializeUserServiceUseCase(context)
}