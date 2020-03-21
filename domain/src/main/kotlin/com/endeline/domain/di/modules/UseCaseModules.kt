package com.endeline.domain.di.modules

import com.endeline.domain.usecase.GetLatestUseCase
import com.endeline.domain.dummy.SaveDummyUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModules {

    @Provides
    fun provideSaveDummyUseCase() = SaveDummyUseCase()

    @Provides
    fun provideGetLatestUseCase() =
        GetLatestUseCase()
}
