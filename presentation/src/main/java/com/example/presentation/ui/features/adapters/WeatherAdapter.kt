package com.example.presentation.ui.features.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.commons.constants.Constants.COUNTRY
import com.example.commons.constants.Constants.EMPTY_STRING
import com.example.commons.constants.Constants.FORMAT_IN
import com.example.commons.constants.Constants.FORMAT_OUT
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
import com.example.commons.constants.Constants.RAIN_UNIT
import com.example.commons.constants.Constants.REGION
import com.example.commons.constants.Constants.TEMPERATURE_UNIT
import com.example.commons.constants.Constants.clearDay
import com.example.commons.constants.Constants.cloudDay
import com.example.commons.constants.Constants.cloudyDay
import com.example.commons.constants.Constants.rainDay
import com.example.commons.constants.Constants.snowDay
import com.example.data.model.WeatherModelResponse
import com.example.presentation.R
import com.example.presentation.databinding.CardContentBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by joseojalvo on 2020-08-06
 */
class WeatherAdapter(private var data: WeatherModelResponse) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

// =================================================================================================
// Attributes
// =================================================================================================

    /**
     * Lateinit variables are used when we can't initialize it but we don't want it to be nullable,
     * since Kotlin requires that a variable is initialized when declared this is an alternative,
     * this should be used for variables which value depend on an injection or an init method.
     *
     * IMPORTANT: If the variable isn't initialized it'll throw an exception, this shouldn't be a
     * common way to declare variables, only when it's required its use will be accepted!
     */
    lateinit var binding: ViewBinding

// =================================================================================================
// Config
// =================================================================================================

    /**
     * When the ViewHolder is created the ViewBinding is inflated and sent to the ViewHolder to
     * be operated when the required data is received
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CardContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    /**
     * When the ViewHolder [holder] is created the instance of the ViewBinding is received and the
     * data, all the logic the adapter should do must be in this method.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.binding is CardContentBinding) {
            holder.binding.temperature.text =
                data.weatherDetailedListData[position].temp2m.toString()
            holder.binding.temperatureUnit.text = TEMPERATURE_UNIT

            setWeatherIcon(
                data.weatherDetailedListData[position].weather, holder.binding.weatherIcon
            )
            setRainAmount(
                data.weatherDetailedListData[position].prec_amount, holder.binding.rainAmount
            )
            setRainAmountIcon(holder.binding.rainAmountIcon)
            setRainAmountUnit(holder.binding.rainAmountUnit)
            setDate(data.initialDate, holder.binding.date, position)
        }
    }

    /**
     * Returns the list of items to be displayed and the times the adapter will iterate to
     * show items
     */
    override fun getItemCount(): Int {
        return data.weatherDetailedListData.size
    }

// =================================================================================================
//  Private methods
// =================================================================================================

    private fun setWeatherIcon(weather: String, weatherIcon: ImageView) {
        when {
            clearDay.contains(weather) -> weatherIcon.setImageResource(R.mipmap.sun)

            cloudyDay.contains(weather)
            -> weatherIcon.setImageResource(R.mipmap.partly_cloudy)

            cloudDay.contains(weather)
            -> weatherIcon.setImageResource(R.mipmap.clouds)

            rainDay.contains(weather) -> weatherIcon.setImageResource(R.mipmap.rain)
            snowDay.contains(weather) -> weatherIcon.setImageResource(R.mipmap.snow)
        }
    }

    private fun setRainAmount(rainAmount: Int, rainAmountText: TextView) {
        rainAmountText.text = when (rainAmount) {
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

    private fun setDate(date: String, dateText: TextView, position: Int) {
        val calendar = Calendar.getInstance()

        val dateParser = SimpleDateFormat(FORMAT_IN, Locale(REGION, COUNTRY))
        val dateFormatter = SimpleDateFormat(FORMAT_OUT, Locale(REGION, COUNTRY))

        val dateViewText = DateFormat.getDateInstance(
            DateFormat.MEDIUM,
            Locale(REGION, COUNTRY)
        )

        var parsedDate = EMPTY_STRING
        dateParser.parse(date)?.let {
            parsedDate = dateFormatter.format(it)
        }
        dateFormatter.parse(parsedDate)?.let {
            calendar.time = it
        }
        calendar.add(Calendar.DATE, position + 1)
        dateText.text = dateViewText.format(calendar.time)
    }

    private fun setRainAmountIcon(rainAmountIcon: ImageView) {
        rainAmountIcon.setImageResource(R.mipmap.rain_amount)
    }

    private fun setRainAmountUnit(rainAmountUnit: TextView) {
        rainAmountUnit.text = RAIN_UNIT
    }

// =================================================================================================
//  ViewHolder
// =================================================================================================

    /**
     * ViewHolder class that contains the ViewBinding instance of the view the adapter will display
     */
    class ViewHolder(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: ViewBinding = itemView
    }
}