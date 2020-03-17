package com.endeline.data.di.modules

import com.endeline.data.dummy.DummyRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    @Provides
    fun provideDummyRepository() = DummyRepository()

}
