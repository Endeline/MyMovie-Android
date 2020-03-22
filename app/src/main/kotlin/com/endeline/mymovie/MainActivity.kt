package com.endeline.mymovie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.endeline.mymovie.databinding.ActivityMainBinding
import com.endeline.mymovie.databinding.ToolbarBinding
import timber.log.Timber
import timber.log.Timber.DebugTree

class MainActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    private lateinit var layoutBinding: ActivityMainBinding
    private lateinit var toolbarBinding: ToolbarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        layoutBinding = ActivityMainBinding.inflate(layoutInflater)
        toolbarBinding = ToolbarBinding.inflate(layoutInflater)

        setContentView(layoutBinding.root)

        init()
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
