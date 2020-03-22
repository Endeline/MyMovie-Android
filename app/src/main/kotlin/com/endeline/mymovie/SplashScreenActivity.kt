package com.endeline.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.endeline.mymovie.databinding.ActivitySplashScreenBinding
import com.endeline.mymovie.di.components.DaggerViewModelComponent
import com.endeline.mymovie.viewmodels.SplashViewModel
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
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

    private fun init() =
        with(viewModel) {
            loadData().subscribeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it) {
                        with(this@SplashScreenActivity) {
                            MainActivity.start(this)
                            this.finish()
                        }
                    } else {
                        showError()
                    }
                }, {
                    showError()
                    Timber.e(it)
                })
            loadData()
        }

    private fun showError() =
        Snackbar.make(binding.root, R.string.loading_error, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.refresh) {
                viewModel.loadData()
            }
            .show()

}
