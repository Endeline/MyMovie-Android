package com.endeline.data.di.modules

import com.endeline.data.service.ProductService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModules {

    @Provides
    fun provideProductService() = PRODUCT_SERVICE

    companion object {
        //todo correct singleton
        private val PRODUCT_SERVICE = ProductService()
    }
}
