package com.endeline.domain.di.components

import com.endeline.data.di.modules.RepositoryModules
import com.endeline.domain.usecase.GetLatestUseCase
import dagger.Component

@Component(modules = [RepositoryModules::class])
interface UseCaseComponent {

    fun inject(useCase: GetLatestUseCase)

}
