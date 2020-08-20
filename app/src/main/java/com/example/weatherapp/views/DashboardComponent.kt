package com.example.weatherapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.weatherapp.R
import com.example.weatherapp.Utils.Constants
import com.example.weatherapp.model.WeatherModelResponse
import kotlinx.android.synthetic.main.dashboard_banner.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by joseojalvo on 2020-08-19
 */

/**
 * Component to show the data of the first position of the data list
 */
class DashboardComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

// =================================================================================================
//  Initialization methods
// =================================================================================================

    /**
     * Initialises the required data
     */
    fun init(data: WeatherModelResponse) {
        bindLayout()
        setWeatherIcon(data.weatherDetailedListData[Constants.FIRST_POSITION].weather)
        setRainAmount(data.weatherDetailedListData[Constants.FIRST_POSITION].prec_amount)
        setRainAmountIcon()
        setRainAmountUnit()
        setDate(data.initialDate)

        temperature.text = data.weatherDetailedListData[Constants.FIRST_POSITION].temp2m.toString()
        temperatureUnit.text = Constants.TEMPERATURE_UNIT
    }

// =================================================================================================
//  Config view
// =================================================================================================

    /**
     * Called to bind the layout
     */
    private fun bindLayout() {
        View.inflate(context, R.layout.dashboard_banner, this)
    }

    /**
     * Called to set weather icons
     */
    private fun setWeatherIcon(weather: String) {
        when {
            Constants.clearDay.contains(weather) -> weatherIcon.setImageResource(R.mipmap.sun)

            Constants.cloudyDay.contains(weather)
            -> weatherIcon.setImageResource(R.mipmap.partly_cloudy)

            Constants.cloudDay.contains(weather)
            -> weatherIcon.setImageResource(R.mipmap.clouds)

            Constants.rainDay.contains(weather) -> weatherIcon.setImageResource(R.mipmap.rain)
            Constants.snowDay.contains(weather) -> weatherIcon.setImageResource(R.mipmap.snow)
        }
    }

    /**
     * Called to set rain amount data
     */
    private fun setRainAmount(rainAmount: Int) {
        when (rainAmount) {
            0 -> rainAmountText.text = Constants.RAIN_AMOUNT_0
            1 -> rainAmountText.text = Constants.RAIN_AMOUNT_1
            2 -> rainAmountText.text = Constants.RAIN_AMOUNT_2
            3 -> rainAmountText.text = Constants.RAIN_AMOUNT_3
            4 -> rainAmountText.text = Constants.RAIN_AMOUNT_4
            5 -> rainAmountText.text = Constants.RAIN_AMOUNT_5
            6 -> rainAmountText.text = Constants.RAIN_AMOUNT_6
            7 -> rainAmountText.text = Constants.RAIN_AMOUNT_7
            8 -> rainAmountText.text = Constants.RAIN_AMOUNT_8
            9 -> rainAmountText.text = Constants.RAIN_AMOUNT_9
        }
    }

    /**
     * Parses the date and prints it
     */
    private fun setDate(date: String) {

        val dateParser =
            SimpleDateFormat(Constants.FORMAT_IN, Locale(Constants.REGION, Constants.COUNTRY))
        val dateFormatter =
            SimpleDateFormat(Constants.FORMAT_OUT, Locale(Constants.REGION, Constants.COUNTRY))

        val dateViewText = DateFormat.getDateInstance(
            DateFormat.MEDIUM,
            Locale(Constants.REGION, Constants.COUNTRY)
        )

        val parsedDate = dateFormatter.format(dateParser.parse(date)!!)
        dateText.text = dateViewText.format(dateFormatter.parse(parsedDate)!!)
    }

    private fun setRainAmountIcon() {
        rainAmountIcon.setImageResource(R.mipmap.rain_amount)
    }

    private fun setRainAmountUnit() {
        rainAmountUnit.text = Constants.RAIN_UNIT
    }
}