package com.example.domain.repository.datasource

import androidx.lifecycle.MutableLiveData
import com.example.data.model.WeatherModelQuery
import com.example.data.model.WeatherModelResponse
import com.example.domain.network.AppException
import com.example.domain.network.resource.Resource
import com.example.domain.repository.repositories.api.ApiRepository
import com.example.utils.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherDataSource {

// =================================================================================================
//  REST methods
// =================================================================================================

    /**
     * Retrieves weather data from an external API and saves the data in [WeatherModelResponse]
     * @param input data to be sent to the endpoint
     * @return the weather data received from the endpoint
     */
    fun getWeatherData(input: WeatherModelQuery): MutableLiveData<Resource<WeatherModelResponse?>> {
        val data = MutableLiveData<Resource<WeatherModelResponse?>>()
        val service = NetworkUtils.initServiceLog().create(ApiRepository::class.java)

        input.longitude?.let { longitude ->
            input.latitude?.let { latitude ->
                input.product?.let { product ->
                    input.output?.let { output ->
                        service.getWeather(longitude, latitude, product, output)
                            .enqueue(object : Callback<WeatherModelResponse> {
                                override fun onResponse(
                                    call: Call<WeatherModelResponse>?,
                                    response: Response<WeatherModelResponse>?
                                ) {
                                    if (response != null && response.isSuccessful) {
                                        data.value = Resource.success(response.body())
                                    } else {
                                        val code = response?.code()
                                        val resource: Resource<WeatherModelResponse?> =
                                            Resource.success(response?.body())
                                        code?.let {
                                            resource.code = it
                                        }
                                        data.value = resource
                                    }
                                }

                                override fun onFailure(
                                    call: Call<WeatherModelResponse>,
                                    t: Throwable
                                ) {
                                    val exception =
                                        AppException(t) //TODO: the throwable has the error code?
                                    data.value = Resource.error(exception)
                                }
                            })
                    }
                }
            }
        }
        return data
    }
}