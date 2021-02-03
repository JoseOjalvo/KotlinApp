package com.example.utils.weather

import com.example.commons.constants.Constants
import com.example.commons.constants.Constants.EMPTY_STRING
import com.example.commons.constants.Constants.RAIN_AMOUNT_0
import com.example.commons.constants.Constants.RAIN_AMOUNT_1
import com.example.commons.constants.Constants.RAIN_AMOUNT_2
import com.example.commons.constants.Constants.RAIN_AMOUNT_3
import com.example.commons.constants.Constants.RAIN_AMOUNT_4
import com.example.commons.constants.Constants.RAIN_AMOUNT_5
import com.example.commons.constants.Constants.RAIN_AMOUNT_6
import com.example.commons.constants.Constants.RAIN_AMOUNT_7
import com.example.commons.constants.Constants.RAIN_AMOUNT_8
import com.example.commons.constants.Constants.RAIN_AMOUNT_9
import com.example.commons.constants.Constants.RESOURCE_NOT_FOUND
import com.example.commons.constants.Constants.clearDay
import com.example.commons.constants.Constants.cloudDay
import com.example.commons.constants.Constants.cloudyDay
import com.example.commons.constants.Constants.rainDay
import com.example.commons.constants.Constants.snowDay
import com.example.utils.R

/**
 *   @author Jose Alejandro Ojalvo
 *   @since 02/02/2021
 *   @email jojalvo@topdoctors.es
 **/
object WeatherUtils {

// =================================================================================================
// Public methods
// =================================================================================================

    /**
     * It checks if the lists contains the weather string provided by the endpoint to set the
     * weather icon to the card component
     */
    fun getWeatherIcon(weather: String): Int {
        return when {
            clearDay.contains(weather) -> R.mipmap.sun
            rainDay.contains(weather) -> R.mipmap.rain
            snowDay.contains(weather) -> R.mipmap.snow
            cloudDay.contains(weather) -> R.mipmap.clouds
            cloudyDay.contains(weather) -> R.mipmap.partly_cloudy
            else -> RESOURCE_NOT_FOUND
        }
    }

    fun getRainAmount(rainAmount: Int): String {/**
     * When expressions are the kotlin equivalent to a switch in java, they use a lambda
     * expression type to refer to the possible values to handle
     */
        return when (rainAmount) {
            0 -> RAIN_AMOUNT_0
            1 -> RAIN_AMOUNT_1
            2 -> RAIN_AMOUNT_2
            3 -> RAIN_AMOUNT_3
            4 -> RAIN_AMOUNT_4
            5 -> RAIN_AMOUNT_5
            6 -> RAIN_AMOUNT_6
            7 -> RAIN_AMOUNT_7
            8 -> RAIN_AMOUNT_8
            9 -> RAIN_AMOUNT_9
            else -> EMPTY_STRING
        }
    }
}