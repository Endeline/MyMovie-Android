package com.endeline.movielibrary.ui.gui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.endeline.movielibrary.BuildConfig
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.ActivityMainBinding
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by viewModels<MainActivityViewModel> {
        viewModelFactory
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val topLevelDestination = setOf(
        R.id.home_fragment,
        R.id.movies_fragment,
        R.id.tv_fragment,
        R.id.user_fragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        init()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun init() = with(binding) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        viewModel.initializeServices(this@MainActivity)

        navigationController = findNavController(R.id.navigation_host)
        appBarConfiguration = AppBarConfiguration(topLevelDestination)

        navigationView.setupWithNavController(navigationController)

        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            navigationView.visibility = if (destination.id == R.id.video_fragment) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }

        navigationView.menu.findItem(R.id.home_fragment).isVisible = false
//        navigationView.menu.findItem(R.id.user_fragment).isVisible = false //todo probably move to toolbar
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
