package com.endeline.mymovie.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.endeline.mymovie.NavigationGraphXmlDirections
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.ActivityMainBinding
import com.endeline.mymovie.di.ViewModelFactory
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private val viewModelFactory: ViewModelFactory.MainActivityViewModel =
        ViewModelFactory.MainActivityViewModel()

    private val viewModel by viewModels<MainActivityViewModel>(factoryProducer = { viewModelFactory })

    private lateinit var binding: ActivityMainBinding

    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val topLevelDestination = setOf(
        R.id.nowPlaying,
        R.id.popular,
        R.id.topRated,
        R.id.upcoming
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

    @SuppressLint("CheckResult")
    private fun init() {
        Timber.plant(DebugTree())

        viewModel.initializeServices(this)

        navigationController = findNavController(R.id.navigation_host)
        appBarConfiguration = AppBarConfiguration(topLevelDestination)

        binding.navigationView.setupWithNavController(navigationController)

        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            binding.navigationView.visibility = if (destination.id == R.id.videoFragment) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
    }

}
