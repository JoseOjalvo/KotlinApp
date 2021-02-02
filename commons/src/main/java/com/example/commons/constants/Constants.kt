package com.example.commons.constants

/**
 * Created by joseojalvo on 2020-08-05
 */
object Constants {
    /**
     * Object classes are the same as static in Java
     */
// =================================================================================================
//  URL Parameters
// =================================================================================================

    const val BASE_URL: String = "http://www.7timer.info"
    const val PRODUCT: String = "civil"
    const val OUTPUT: String = "json"

// =================================================================================================
//  Weather Units
// =================================================================================================

    const val TEMPERATURE_UNIT: String = "ºC"

// =================================================================================================
//  Rain parameters
// =================================================================================================

    const val RAIN_AMOUNT_0: String = "0"
    const val RAIN_AMOUNT_1: String = "0-0.25"
    const val RAIN_AMOUNT_2: String = "0.25-1"
    const val RAIN_AMOUNT_3: String = "1-4"
    const val RAIN_AMOUNT_4: String = "4-10"
    const val RAIN_AMOUNT_5: String = "10-16"
    const val RAIN_AMOUNT_6: String = "16-30"
    const val RAIN_AMOUNT_7: String = "30-50"
    const val RAIN_AMOUNT_8: String = "50-75"
    const val RAIN_AMOUNT_9: String = "<75"

    const val RAIN_UNIT: String = "mm/hr"

// =================================================================================================
//  Weather type
// =================================================================================================

    val clearDay = listOf("clearday,", "clearnight")
    val cloudyDay = listOf("humidday", "humidnight", "pcloudyday", "pcloudynight")
    val cloudDay = listOf("mcloudyday", "mcloudynight", "cloudyday", "cloudynight")
    val snowDay = listOf("snowday", "snownight", "rainsnowday", "rainsnownight")
    val rainDay = listOf(
        "lightrainday", "lightrainnight", "oshowerday", "oshowernight",
        "ishowerday", "ishowernight", "rainday", "rainnight"
    )

// =================================================================================================
//  File names
// =================================================================================================

    const val LOADER_NAME: String = "loader.json"

// =================================================================================================
//  Location
// =================================================================================================

    const val LONGITUDE: Double = 41.388
    const val LATITUDE: Double = 2.158

    const val REGION: String = "es"
    const val COUNTRY: String = "ES"

// =================================================================================================
//  Date formats
// =================================================================================================

    const val FORMAT_IN: String = "yyyyMMddHH"
    const val FORMAT_OUT: String = "dd-MM-yyyy"

// =================================================================================================
//  REST behaviour
// =================================================================================================

    const val SERVICE_FAILURE = "SERVICE_FAILURE"

// =================================================================================================
//  Numeric utils
// =================================================================================================

    const val FIRST_POSITION: Int = 0

// =================================================================================================
//  String utils
// =================================================================================================

    const val EMPTY_STRING = ""

}