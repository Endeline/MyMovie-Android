package com.endeline.data.di.components

import com.endeline.data.cache.Cache
import com.endeline.data.di.modules.CacheModule
import com.endeline.data.di.modules.RetrofitModule
import com.endeline.data.service.ProductService
import dagger.Component

@Component(modules = [RetrofitModule::class, CacheModule::class])
interface DataComponent {
    fun inject(productService: ProductService)
    fun inject(cache: Cache)
}