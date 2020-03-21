package com.endeline.mymovie

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.endeline.domain.dummy.DummyUiModel
import com.endeline.domain.usecase.GetLatestUseCase
import com.endeline.domain.dummy.SaveDummyUseCase
import com.endeline.mymovie.databinding.ActivityMainBinding
import com.endeline.mymovie.databinding.ToolbarBinding
import com.endeline.mymovie.di.components.DaggerAppComponent
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    protected lateinit var saveDummyUseCase: SaveDummyUseCase

    @Inject
    protected lateinit var getLatestUseCase: GetLatestUseCase

    private lateinit var layoutBinding: ActivityMainBinding
    private lateinit var toolbarBinding: ToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutBinding = ActivityMainBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.inflate(layoutInflater)

        setContentView(layoutBinding.root)

        init()

        DaggerAppComponent.builder().build().inject(this)

        saveDummyUseCase(DummyUiModel("test", 0))

        getLatestUseCase()
            .subscribeOn(Schedulers.io())
            .subscribe( {
                Timber.d("$it")
            }, Timber::e)

    }

    private fun init() {
        Timber.plant(DebugTree())

        val navigationController = findNavController(R.id.navigation_host)

        layoutBinding.navigationView.setupWithNavController(navigationController)

        with(supportActionBar) {
            this?.setCustomView(
                toolbarBinding.root,
                ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
            )
            this?.setDisplayShowCustomEnabled(true)

            with(toolbarBinding) {
                icSearch.setOnClickListener {
                    navigationController.navigate(R.id.searchFragment)
                    icBack.visibility = View.VISIBLE
                }
                icBack.setOnClickListener {
                    navigationController.popBackStack()
                    icBack.visibility = View.GONE
                }
            }
        }
    }
}
