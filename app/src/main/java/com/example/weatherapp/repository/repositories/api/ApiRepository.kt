package com.example.weatherapp.repository.repositories.api

import com.example.weatherapp.model.WeatherModelResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by joseojalvo on 2020-08-04
 */
interface ApiRepository {

// =================================================================================================
// Attributes
// =================================================================================================

    @GET("/bin/api.pl?")
    @Headers("Content-Type: application/json;charset=UTF-8")
    fun getWeather(
        @Query("lon") longitude : Double,
        @Query("lat") latitude : Double,
        @Query("product") product : String,
        @Query("output") output : String) : Call<WeatherModelResponse>

}