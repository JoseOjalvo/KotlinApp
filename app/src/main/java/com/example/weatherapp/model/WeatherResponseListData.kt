package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by joseojalvo on 2020-08-05
 */
class WeatherResponseListData {

    @SerializedName("timepoint")
    private var timepoint : Int = 0

    @SerializedName("cloudcover")
    private var cloudcover : Int = 0

    @SerializedName("lifted_index")
    private var lifted_index : Int = 0

    @SerializedName("prec_type")
    private var prec_type : Int = 0

    @SerializedName("prec_amount")
    private var prec_amount : Int = 0

    @SerializedName("temp2m")
    private var temp2m : Int = 0

    @SerializedName("rh2m")
    private var rh2m : Int = 0

    @SerializedName("wind10m")
    private var wind10m : WindModelData = WindModelData()

}