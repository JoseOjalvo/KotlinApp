package com.example.data.model

import com.example.commons.constants.Constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName

/**
 * Created by joseojalvo on 2020-08-04
 */
class WeatherModelResponse {

    /**
     * The @SerializedName annotation specifies the name of the field from the JSON received from
     * the endpoint, it automates the process of parsing the data and automatically fills the field
     * of the model with the field from the JSON with that same name.
     */
    @SerializedName("init")
    var initialDate : String = EMPTY_STRING

    @SerializedName("dataseries")
    var weatherDetailedListData = arrayListOf<WeatherResponseListData>()

}