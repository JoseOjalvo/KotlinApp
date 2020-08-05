package com.example.weatherapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by joseojalvo on 2020-08-05
 */
class WindModelData {

    @SerializedName("direction")
    private var direction : Int = 0

    @SerializedName("speed")
    private var speed : Int = 0
}