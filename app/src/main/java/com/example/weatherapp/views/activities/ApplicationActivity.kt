package com.example.weatherapp.views.activities

import android.animation.Animator
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.Utils.Constants
import com.example.weatherapp.modules.WeatherModule.AppModules.dataSourceModule
import com.example.weatherapp.modules.WeatherModule.AppModules.implementationModule
import com.example.weatherapp.modules.WeatherModule.AppModules.useCaseModule
import com.example.weatherapp.modules.WeatherModule.AppModules.viewmodelModule
import kotlinx.android.synthetic.main.splash_screen.*
import org.koin.core.context.startKoin

/**
 * Created by joseojalvo on 2020-08-05
 */
class ApplicationActivity : Activity() {

// =================================================================================================
// Config
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        Log.d("onresume", "splash")
        initKoin()

        initSplashListener()
    }

// =================================================================================================
// Initialization methods
// =================================================================================================

    private fun initSplashListener() {
        splash.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

    private fun initKoin() {
        startKoin {
            modules(listOf(useCaseModule, viewmodelModule, dataSourceModule, implementationModule))
        }
    }
}