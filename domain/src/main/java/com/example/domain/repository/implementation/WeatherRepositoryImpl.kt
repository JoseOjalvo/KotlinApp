package com.example.domain.repository.implementation

import androidx.lifecycle.MutableLiveData
import com.example.data.model.WeatherModelQuery
import com.example.data.model.WeatherModelResponse
import com.example.domain.network.resource.Resource
import com.example.domain.repository.datasource.WeatherDataSource
import com.example.domain.repository.repositories.WeatherRepository
import org.koin.core.inject

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherRepositoryImpl : WeatherRepository {

// =================================================================================================
//  Attributes
// =================================================================================================

    private val datasource : WeatherDataSource by inject()

// =================================================================================================
//  Datasource method
// =================================================================================================

    override fun getWeatherData(input: WeatherModelQuery): MutableLiveData<Resource<WeatherModelResponse?>> {
        return datasource.getWeatherData(input)
    }
}