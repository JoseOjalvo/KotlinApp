package com.example.weatherapp.repository.implementation

import com.example.weatherapp.model.WeatherModelQuery
import com.example.weatherapp.model.WeatherModelResponse
import com.example.weatherapp.repository.datasource.WeatherDataSource
import com.example.weatherapp.repository.repositories.WeatherRepository
import io.reactivex.Observable
import org.koin.core.inject

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherRepositoryImpl : WeatherRepository {

    val datasource : WeatherDataSource by inject()

    override fun getWeatherData(input : WeatherModelQuery): Observable<WeatherModelResponse> {
        return datasource.getWeatherData(input)
    }
}