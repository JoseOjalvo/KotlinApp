package com.example.data.model

import com.example.commons.constants.Constants.OUTPUT
import com.example.commons.constants.Constants.PRODUCT


/**
 * Created by joseojalvo on 2020-08-05
 *
 * Contains the data that'll be sent to the endpoint
 */
class WeatherModelQuery(
    val longitude: Double?,
    val latitude: Double?,
    val product: String?,
    val output: String?
)