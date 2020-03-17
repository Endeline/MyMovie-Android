package com.endeline.mymovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.endeline.domain.dummy.DummyUiModel
import com.endeline.domain.dummy.SaveDummyUseCase
import com.endeline.mymovie.di.components.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    protected lateinit var saveDummyUseCase: SaveDummyUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        DaggerAppComponent.builder().build().inject(this)

        saveDummyUseCase(DummyUiModel("dupa", 0))

    }

    private fun init() {
        Timber.plant(DebugTree())
    }
}
