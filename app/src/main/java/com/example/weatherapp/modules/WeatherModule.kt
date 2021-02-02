package com.example.weatherapp.modules

import com.example.domain.repository.datasource.WeatherDataSource
import com.example.domain.repository.implementation.WeatherRepositoryImpl
import com.example.domain.repository.repositories.WeatherRepository
import com.example.domain.usecases.WeatherUseCase
import com.example.presentation.ui.features.activities.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.dsl.module

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherModule : KoinComponent {

    /**
     * Modules are a way to separate all the elements to be instantiated in the Application class,
     * they are used to specify Koin which classes will be injected.
     *
     * Single -> Specifies that the following class will be instantiated as a Singleton, so Koin
     * will keep that class and its values in local memory until the app process is finished.
     *
     * ViewModel -> Specifies that the following class will be instantiated as a ViewModel
     */

    object AppModules {

        val useCaseModule = module {
            single {
                WeatherUseCase(get())
            }
        }

        val viewmodelModule = module {
            viewModel {
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