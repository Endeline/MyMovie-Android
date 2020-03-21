package com.endeline.domain.di.modules

import com.endeline.domain.usecase.GetLatestUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModules {

    @Provides
    fun provideGetLatestUseCase() =
        GetLatestUseCase()

}
