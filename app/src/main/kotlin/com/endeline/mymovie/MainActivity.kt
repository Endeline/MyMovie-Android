package com.endeline.mymovie

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.endeline.mymovie.databinding.ActivityMainBinding
import com.endeline.mymovie.fragments.nowplaying.NowPlayingFragmentDirections
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private lateinit var binding: ActivityMainBinding

    private lateinit var navigationController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

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

        setSupportActionBar(binding.customToolbar)

        navigationController = findNavController(R.id.navigation_host)
        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.nowPlaying,
                R.id.latest,
                R.id.popular,
                R.id.topRated,
                R.id.upcoming
            )
        )
        setupActionBarWithNavController(navigationController, appBarConfiguration)

        with(binding) {
            navigationView.setupWithNavController(navigationController)
            findViewById<ImageView>(R.id.ic_search).setOnClickListener {
                navigationController.navigate(NowPlayingFragmentDirections.toSearch())
            }
        }
    }

}
