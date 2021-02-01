package com.example.data.model

import com.example.commons.constants.Constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName

/**
 * Created by joseojalvo on 2020-08-04
 */
class WeatherModelResponse {

    @SerializedName("init")
    var initialDate : String = EMPTY_STRING

    @SerializedName("dataseries")
    var weatherDetailedListData = arrayListOf<WeatherResponseListData>()

}