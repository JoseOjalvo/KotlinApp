package com.example.weatherapp.usecase

import com.example.weatherapp.model.WeatherModelQuery
import com.example.weatherapp.model.WeatherModelResponse
import com.example.weatherapp.repository.repositories.WeatherRepository
import io.reactivex.Observable

/**
 * Created by joseojalvo on 2020-08-04
 */
class WeatherUseCase(private val repository : WeatherRepository) : BaseUseCase {

    fun execute(input : WeatherModelQuery) : Observable<WeatherModelResponse> {
        return repository.getWeatherData(input)
    }

}