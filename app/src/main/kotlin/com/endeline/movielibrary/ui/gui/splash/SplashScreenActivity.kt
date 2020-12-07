package com.endeline.movielibrary.ui.gui.splash

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.ActivitySplashScreenBinding
import com.endeline.movielibrary.di.ViewModelFactory
import com.endeline.movielibrary.di.components.DaggerAppComponent
import com.endeline.movielibrary.ui.gui.main.MainActivity
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
        DaggerAppComponent.create().inject(this)
        super.onCreate(savedInstanceState)

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
