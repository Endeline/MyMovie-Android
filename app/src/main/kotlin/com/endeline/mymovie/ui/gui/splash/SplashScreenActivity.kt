package com.endeline.mymovie.ui.gui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.ActivitySplashScreenBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.di.components.DaggerAppComponent
import com.endeline.mymovie.ui.gui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory.SplashScreenViewModelFactory

    private lateinit var binding: ActivitySplashScreenBinding

    private val viewModel by viewModels<SplashViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.create().inject(this)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        subscribeUi()
    }

    private fun subscribeUi() = with(viewModel) {
        dataLoaded.observe(this@SplashScreenActivity) { dataLoaded ->
            if (dataLoaded) {
                MainActivity.start(this@SplashScreenActivity)
                this@SplashScreenActivity.finish()
            } else {
                Snackbar.make(binding.root, R.string.loading_error, Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.refresh) { viewModel.loadData() }
                    .show()
            }
        }
    }
}
