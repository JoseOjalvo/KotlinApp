package com.example.domain.repository.repositories

import androidx.lifecycle.MutableLiveData
import com.example.data.model.WeatherModelQuery
import com.example.data.model.WeatherModelResponse
import com.example.domain.network.resource.Resource
import org.koin.core.KoinComponent

/**
 * Created by joseojalvo on 2020-08-05
 */
interface WeatherRepository : KoinComponent {

    fun getWeatherData(input: WeatherModelQuery): MutableLiveData<Resource<WeatherModelResponse?>>
}