package com.example.data.model

import com.example.commons.constants.Constants.EMPTY_STRING
import com.google.gson.annotations.SerializedName

/**
 * Created by joseojalvo on 2020-08-05
 *
 * Contains additional data of the weather
 */
class WeatherResponseListData(
    val timepoint: Int?,
    val cloudcover: Int?,
    val temp2m: Int?,
    val rh2m: String?,
    val wind10m: WindModelData?,
    val weather: String?,
    /**
     * The @SerializedName annotation specifies the name of the field from the JSON received from
     * the endpoint, it automates the process of parsing the data and automatically fills the field
     * of the model with the field from the JSON with that same name.
     * This should only be used when the JSON name parameter doesn't meet our name requirements
     * or isn't in camel case.
     * If it isn't added and the variable has the same name as the JSON paramenter it'll be
     * automatically filled when the data is received.
     */
    @SerializedName("lifted_index")
    val liftedIndex: Int?,

    @SerializedName("prec_type")
    val precType: String?,

    @SerializedName("prec_amount")
    val precAmount: Int?
)