package com.endeline.data.di.modules

import com.endeline.data.cache.Cache
import com.endeline.data.cache.MemoryCache
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun provideMemoryCache() = MemoryCache()

    @Provides
    fun provideCache() = Cache()
}
