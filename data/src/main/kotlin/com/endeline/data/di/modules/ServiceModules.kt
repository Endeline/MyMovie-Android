package com.endeline.data.di.modules

import com.endeline.data.service.UserService
import dagger.Module
import dagger.Provides

@Module
class ServiceModules {
    companion object {
        val USER_SERVICE = UserService()
    }

    @Provides
    fun provideUserService() = USER_SERVICE
}