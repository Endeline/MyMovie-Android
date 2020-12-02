package com.endeline.data.di.modules

import com.endeline.data.service.UserService
import dagger.Module
import dagger.Provides

@Module
class ServiceModules {

    @Provides
    fun provideUserService() = USER_SERVICE

    companion object {
        private val USER_SERVICE = UserService()
    }
}
