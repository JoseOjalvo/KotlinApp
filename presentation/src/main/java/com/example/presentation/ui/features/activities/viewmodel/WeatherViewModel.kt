package com.example.presentation.ui.features.activities.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.model.WeatherModelQuery
import com.example.data.model.WeatherModelResponse
import com.example.domain.network.resource.Resource
import com.example.domain.usecases.WeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.inject

/**
 * Created by joseojalvo on 2020-08-04
 */
open class WeatherViewModel : BaseViewModel() {

// =================================================================================================
// Use Case
// =================================================================================================

    private val useCase: WeatherUseCase by inject()

// =================================================================================================
// LiveData
// =================================================================================================

    val data: LiveData<WeatherModelResponse?> get() = _data

// =================================================================================================
// MutableLiveData
// =================================================================================================

    private val _data = MutableLiveData<WeatherModelResponse?>()

// =================================================================================================
//  Attributes
// =================================================================================================

    var queryModel: WeatherModelQuery = WeatherModelQuery()

// =================================================================================================
//  REST methods
// =================================================================================================

    /**
     * Calls an endpoint to retrieve the weather data using coroutines
     */
    override fun retrieveData(activity: AppCompatActivity) {
        viewModelScope.launch(Dispatchers.Main) {
            val result: MutableLiveData<Resource<WeatherModelResponse?>> =
                withContext(Dispatchers.IO) { useCase.execute(queryModel) }
            result.observe(activity, { resource ->
                postResource(resource, _data)
            })
        }
    }
}