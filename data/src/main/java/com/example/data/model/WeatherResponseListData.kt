package com.example.data.model

import com.example.commons.constants.Constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherResponseListData {

    @SerializedName("timepoint")
    var timepoint: Int = 0

    @SerializedName("cloudcover")
    var cloudcover: Int = 0

    @SerializedName("lifted_index")
    var lifted_index: Int = 0

    @SerializedName("prec_type")
    var prec_type: String = EMPTY_STRING

    @SerializedName("prec_amount")
    var prec_amount: Int = 0

    @SerializedName("temp2m")
    var temp2m: Int = 0

    @SerializedName("rh2m")
    var rh2m: String = EMPTY_STRING

    @SerializedName("wind10m")
    var wind10m: WindModelData = WindModelData()

    @SerializedName("weather")
    var weather: String = EMPTY_STRING

}