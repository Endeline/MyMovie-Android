package com.endeline.mymovie.ui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.ActivitySplashScreenBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    private val viewModelFactory: ViewModelFactory.SplashScreenViewModelFactory =
        ViewModelFactory.SplashScreenViewModelFactory()

    private val viewModel by viewModels<SplashViewModel>{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        subscribeUi()
    }

    private fun subscribeUi() = with(viewModel) {
        dataLoadedLiveData.observe(this@SplashScreenActivity) { dataLoaded ->
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
