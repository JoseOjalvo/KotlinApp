package com.example.weatherapp.repository.repositories

import com.example.weatherapp.model.WeatherModelQuery
import com.example.weatherapp.model.WeatherModelResponse
import io.reactivex.Observable
import org.koin.core.KoinComponent

/**
 * Created by joseojalvo on 2020-08-05
 */
interface WeatherRepository : KoinComponent {

    fun getWeatherData(input: WeatherModelQuery): Observable<WeatherModelResponse>
}