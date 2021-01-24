package com.endeline.movielibrary.di.components

import android.app.Application
import com.endeline.movielibrary.di.modules.ActivityBuildersModule
import com.endeline.movielibrary.di.modules.RetrofitModule
import com.endeline.movielibrary.di.modules.ViewModelFactoryModule
import com.endeline.movielibrary.ui.gui.base.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        ViewModelFactoryModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}
