package com.endeline.mymovie.di.modules

import com.endeline.domain.dummy.SaveDummyUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModules {

    @Provides
    fun provideSaveDummyUseCase() = SaveDummyUseCase()

}
