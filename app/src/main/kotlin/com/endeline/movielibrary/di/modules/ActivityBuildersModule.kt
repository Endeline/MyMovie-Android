package com.endeline.movielibrary.di.modules

import com.endeline.movielibrary.ui.gui.main.MainActivity
import com.endeline.movielibrary.ui.gui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class, ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeSplashScreenActivity(): SplashScreenActivity
}
