package com.example.weatherapp.repository.datasource

import android.util.Log
import com.example.weatherapp.Utils.Constants
import com.example.weatherapp.model.WeatherModelQuery
import com.example.weatherapp.model.WeatherModelResponse
import com.example.weatherapp.repository.repositories.api.ApiRepository
import io.reactivex.Observable
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
    private var product: String = ""
    private var output = ""

// =================================================================================================
//  REST methods
// =================================================================================================

    /**
     * Retrieves weather data from API and saves
     * the data in [WeatherModelResponse]
     */
    fun getWeatherData(input: WeatherModelQuery): Observable<WeatherModelResponse> {

        parseData(input)

        val service = initServiceLog().create(ApiRepository::class.java)

        val call = service.getWeather(longitude, latitude, product, output)


        return Observable.create {emitter ->

            call.enqueue(object : Callback<WeatherModelResponse> {
                override fun onResponse(
                    call: Call<WeatherModelResponse>,
                    response: Response<WeatherModelResponse>
                ) {
                    if (response.code() == 200) {
                        emitter.onNext(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<WeatherModelResponse>, t: Throwable) {
                    Log.d("SERVICE_FAILURE", t.message!!)
                }
            })
        }
    }

// =================================================================================================
//  Private methods
// =================================================================================================

    private fun parseData(data: WeatherModelQuery) {
        latitude = data.latitude
        longitude = data.longitude
        product = data.product
        output = data.output
    }

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