package com.example.data.model

/**
 * Created by joseojalvo on 2020-08-04
 *
 * Contains the data that'll be received in the endpoint
 */
class WeatherModelResponse(
    val init : String?,
    val dataseries: ArrayList<WeatherResponseListData>?
)