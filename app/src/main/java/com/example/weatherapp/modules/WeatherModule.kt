package com.example.weatherapp.modules

import com.example.weatherapp.repository.datasource.WeatherDataSource
import com.example.weatherapp.repository.implementation.WeatherRepositoryImpl
import com.example.weatherapp.repository.repositories.WeatherRepository
import com.example.weatherapp.usecase.WeatherUseCase
import com.example.weatherapp.viewmodel.WeatherViewModel
import org.koin.core.KoinComponent
import org.koin.dsl.module

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherModule : KoinComponent {

    object AppModules {

        val useCaseModule = module {
            single {
                WeatherUseCase(get())
            }
        }

        val viewmodelModule = module {
            factory {
                WeatherViewModel()
            }
        }

        val dataSourceModule = module {
            single {
                WeatherDataSource()
            }
        }

        val implementationModule = module {
            single<WeatherRepository> { WeatherRepositoryImpl() }
        }
    }

}