package com.example.presentation.ui.features.activities.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.commons.constants.Constants.LATITUDE
import com.example.commons.constants.Constants.LONGITUDE
import com.example.commons.constants.Constants.OUTPUT
import com.example.commons.constants.Constants.PRODUCT
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

    /**
     * It contains the call to the endpoint, use cases should always be injected to be able to use
     * them everywhere in the app, this way we can also provide them the dependencies to the
     * repository to be able to call the Data Source's endpoint.
     */
    private val useCase: WeatherUseCase by inject()

// =================================================================================================
// LiveData
// =================================================================================================

    /**
     * [LiveData] is a container of data that can be observed, but the difference with other
     * observable items is that this is optimized for lifecycles, meaning that it'll respect the
     * lifecycle of the Activity/Fragment, with this we'll ensure that it'll only exist when the
     * lifecycle is valid.
     *
     * With this we can forget about memory leaks when we leave a fragment, because it'll stop
     * existing in the application context.
     */
    val data: LiveData<WeatherModelResponse?> get() = _data

// =================================================================================================
// MutableLiveData
// =================================================================================================

    /**
     * [MutableLiveData] behaves the same way as LiveData does but with the difference that the
     * MutableLiveData can update its own value with the setValue() and postValue() methods.
     *
     * [MutableLiveData] should only be used in the ViewModel, the exposed attribute to the
     * observers should always be an immutable attribute as [LiveData] is
     */
    private val _data = MutableLiveData<WeatherModelResponse?>()

// =================================================================================================
//  Attributes
// =================================================================================================

    /**
     * Model used to send the required data to the endpoint
     */
    var queryModel: WeatherModelQuery = WeatherModelQuery(LONGITUDE, LATITUDE, PRODUCT, OUTPUT)

// =================================================================================================
//  REST methods
// =================================================================================================

    /**
     * Calls an endpoint to retrieve the weather data using coroutines.
     * ViewModelScope contains the scope of the actual ViewModel, it'll contain the coroutines.
     *
     * Coroutines allow us to have asynchronous tasks that would otherwise block the code for too
     * long, we use them to call the endpoints, as they are asynchronous the main thread keeps
     * working on the UI until the observer is triggered, meaning some data has arrived.
     *
     * @param activity required to get the lifecycle owner necessary to observe the endpoint call
     */
    override fun retrieveData(activity: AppCompatActivity) {
        viewModelScope.launch(Dispatchers.Main) {
            val result: MutableLiveData<Resource<WeatherModelResponse?>> =
                withContext(Dispatchers.Default) { useCase.execute(queryModel) }
            result.observe(activity, { resource ->
                postResource(resource, _data)
            })
        }
    }
}