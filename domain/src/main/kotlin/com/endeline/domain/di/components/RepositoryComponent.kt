package com.endeline.domain.di.components

import com.endeline.data.di.modules.RepositoryModules
import com.endeline.domain.dummy.SaveDummyUseCase
import com.endeline.domain.dummy.TrackDummyUseCase
import dagger.Component

@Component(modules = [RepositoryModules::class])
interface RepositoryComponent {
    fun inject(useCases: SaveDummyUseCase)
    fun inject(useCase: TrackDummyUseCase)
}
