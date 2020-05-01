package com.endeline.mymovie.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.ActivitySplashScreenBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.ui.main.MainActivity
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class SplashScreenActivity : AppCompatActivity() {

    @Inject
    protected lateinit var viewModel: SplashViewModel

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        DaggerViewModelComponent.builder().build().inject(this)

        init()
    }

    private fun init() {
        viewModel.apply {
            dataLoadedLiveData.observe(this@SplashScreenActivity, Observer {
                if (it) {
                    MainActivity.start(this@SplashScreenActivity)
                    this@SplashScreenActivity.finish()
                } else {
                    Snackbar.make(binding.root, R.string.loading_error, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.refresh) { viewModel.loadData() }
                        .show()
                }
            })

            loadData()
        }
    }

}
