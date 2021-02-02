package com.example.commons.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.commons.R
import com.example.commons.constants.Constants
import com.example.commons.constants.Constants.FIRST_POSITION
import com.example.commons.constants.Constants.clearDay
import com.example.commons.constants.Constants.cloudDay
import com.example.commons.constants.Constants.cloudyDay
import com.example.commons.constants.Constants.rainDay
import com.example.commons.constants.Constants.snowDay
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by joseojalvo on 2020-08-19
 *
 * Custom component to show the data of the first position of the data list
 */
class DashboardComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

// =================================================================================================
//  Attributes
// =================================================================================================

    var dateText: TextView? = null
    var temperature: TextView? = null
    var weatherIcon: ImageView? = null
    var rainAmountIcon: ImageView? = null
    var rainAmountUnit: TextView? = null
    var rainAmountText: TextView? = null
    var temperatureUnit: TextView? = null

// =================================================================================================
//  Initialization methods
// =================================================================================================

    /**
     * Parses the received data to information displayable for the component
     */
    fun init() {
        bindLayout()
        initViews()
        setRainAmountIcon()
        setRainAmountUnit()
        temperatureUnit?.text = Constants.TEMPERATURE_UNIT
    }

    /**
     * Initializes the required views
     */
    private fun initViews() {
        dateText = findViewById(R.id.dateText)
        temperature = findViewById(R.id.temperature)
        weatherIcon = findViewById(R.id.weatherIcon)
        rainAmountText = findViewById(R.id.rainAmountText)
        rainAmountUnit = findViewById(R.id.rainAmountUnit)
        temperatureUnit = findViewById(R.id.temperatureUnit)
        temperatureUnit = findViewById(R.id.temperatureUnit)
    }

// =================================================================================================
//  Config view
// =================================================================================================

    /**
     * Called to bind the layout and inflate the view
     */
    private fun bindLayout() {
        View.inflate(context, R.layout.dashboard_banner, this)
    }

    private fun setRainAmountIcon() {
        rainAmountIcon?.setImageResource(R.mipmap.rain_amount)
    }

    private fun setRainAmountUnit() {
        rainAmountUnit?.text = Constants.RAIN_UNIT
    }
}