package com.endeline.data.di.modules

import com.endeline.data.cache.Cache
import com.endeline.data.cache.MemoryCache
import dagger.Module
import dagger.Provides

@Module
class CacheModule {

    @Provides
    fun provideCache() = CACHE

    @Provides
    fun provideMemoryCache() = MEMORY_CACHE

    companion object {
        private val CACHE = Cache()
        private val MEMORY_CACHE = MemoryCache()
    }
}