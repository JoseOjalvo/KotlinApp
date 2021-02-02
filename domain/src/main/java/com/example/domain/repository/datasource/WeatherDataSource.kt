package com.example.domain.repository.datasource

import androidx.lifecycle.MutableLiveData
import com.example.commons.constants.Constants
import com.example.commons.constants.Constants.EMPTY_STRING
import com.example.data.model.WeatherModelQuery
import com.example.data.model.WeatherModelResponse
import com.example.domain.network.AppException
import com.example.domain.network.resource.Resource
import com.example.domain.repository.repositories.api.ApiRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherDataSource {

// =================================================================================================
//  Attributes
// =================================================================================================

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var product: String = EMPTY_STRING
    private var output = EMPTY_STRING

// =================================================================================================
//  REST methods
// =================================================================================================

    /**
     * Retrieves weather data from an external API and saves the data in [WeatherModelResponse]
     */
    fun getWeatherData(input: WeatherModelQuery): MutableLiveData<Resource<WeatherModelResponse?>> {

        parseData(input)

        val data = MutableLiveData<Resource<WeatherModelResponse?>>()
        val service = initServiceLog().create(ApiRepository::class.java)
        service.getWeather(longitude, latitude, product, output).enqueue(object :
            Callback<WeatherModelResponse> {
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

            override fun onFailure(call: Call<WeatherModelResponse>, t: Throwable) {
                val exception = AppException(t)
                data.value = Resource.error(exception)
            }
        })
        return data
    }

// =================================================================================================
//  Private methods
// =================================================================================================

    /**
     * Extracts the data received from the model and stores it in local variables
     */
    private fun parseData(data: WeatherModelQuery) {
        latitude = data.latitude
        longitude = data.longitude
        product = data.product
        output = data.output
    }

    /**
     * Init the RetrofitService that'll allow us to call endpoints
     */
    private fun initServiceLog(): Retrofit {

        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
}