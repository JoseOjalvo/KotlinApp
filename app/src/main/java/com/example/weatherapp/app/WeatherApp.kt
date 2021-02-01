package com.example.weatherapp.app

import android.app.Application
import com.example.weatherapp.modules.WeatherModule.AppModules.dataSourceModule
import com.example.weatherapp.modules.WeatherModule.AppModules.implementationModule
import com.example.weatherapp.modules.WeatherModule.AppModules.useCaseModule
import com.example.weatherapp.modules.WeatherModule.AppModules.viewmodelModule
import org.koin.core.context.startKoin

/**
 *   @author Jose Alejandro Ojalvo
 *   @since 01/02/2021
 *   @email jojalvo@topdoctors.es
 **/
class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            modules(listOf(useCaseModule, viewmodelModule, dataSourceModule, implementationModule))
        }
    }

}