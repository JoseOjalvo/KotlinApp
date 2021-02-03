package com.example.domain.usecases

import androidx.lifecycle.MutableLiveData
import com.example.data.model.WeatherModelQuery
import com.example.data.model.WeatherModelResponse
import com.example.domain.network.resource.Resource
import com.example.domain.repository.repositories.WeatherRepository

/**
 * Created by joseojalvo on 2020-08-04
 */
class WeatherUseCase(private val repository : WeatherRepository) : BaseUseCase {

    /**
     * Implements the repository to call the weather data endpoint
     * @param input data to be sent to the endpoint
     * @return the weather data received from the endpoint
     */
    fun execute(input : WeatherModelQuery) : MutableLiveData<Resource<WeatherModelResponse?>> {
        return repository.getWeatherData(input)
    }

}