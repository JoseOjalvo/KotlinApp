package com.example.weatherapp.app

import android.app.Application
import com.example.weatherapp.modules.WeatherModule.AppModules.dataSourceModule
import com.example.weatherapp.modules.WeatherModule.AppModules.implementationModule
import com.example.weatherapp.modules.WeatherModule.AppModules.useCaseModule
import com.example.weatherapp.modules.WeatherModule.AppModules.viewmodelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

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

    /**
     * Initializes the Koin modules.
     *
     * Koin is a dependency injector that simplifies the dependency between classes, it also helps
     * us to use singletons to store data and keeps these instances alive during the whole process
     * of the app.
     */
    private fun initKoin() {
        startKoin {
            modules(listOf(useCaseModule, viewmodelModule, dataSourceModule, implementationModule))
        }
    }

}