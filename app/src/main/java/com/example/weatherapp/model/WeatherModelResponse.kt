package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by joseojalvo on 2020-08-04
 */
class WeatherModelResponse {

    @SerializedName("init")
    var initialDate : String = ""

    @SerializedName("dataseries")
    var weatherDetailedListData = arrayListOf<WeatherResponseListData>()

}