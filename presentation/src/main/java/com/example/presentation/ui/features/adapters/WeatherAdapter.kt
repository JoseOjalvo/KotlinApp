package com.example.presentation.ui.features.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.commons.constants.Constants
import com.example.data.model.WeatherModelResponse
import com.example.presentation.R
import com.example.presentation.databinding.CardContentBinding
import com.example.utils.extensions.inflate
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

    lateinit var binding: ViewBinding

// =================================================================================================
// Config
// =================================================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CardContentBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.binding is CardContentBinding) {
            holder.binding.temperature.text =
                data.weatherDetailedListData[position].temp2m.toString()
            holder.binding.temperatureUnit.text = Constants.TEMPERATURE_UNIT

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

    override fun getItemCount(): Int {
        return data.weatherDetailedListData.size
    }

    private fun setWeatherIcon(weather: String, weatherIcon: ImageView) {
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

    // =================================================================================================
    //  Private methods
    // =================================================================================================

    private fun setRainAmount(rainAmount: Int, rainAmountText: TextView) {
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

    private fun setDate(date: String, dateText: TextView, position: Int) {
        val calendar = Calendar.getInstance()

        val dateParser = SimpleDateFormat(
            Constants.FORMAT_IN,
            Locale(Constants.REGION, Constants.COUNTRY)
        )
        val dateFormatter = SimpleDateFormat(
            Constants.FORMAT_OUT,
            Locale(Constants.REGION, Constants.COUNTRY)
        )

        val dateViewText = DateFormat.getDateInstance(
            DateFormat.MEDIUM,
            Locale(Constants.REGION, Constants.COUNTRY)
        )

        val parsedDate = dateFormatter.format(dateParser.parse(date)!!)
        calendar.time = dateFormatter.parse(parsedDate)!!
        calendar.add(Calendar.DATE, position + 1)
        dateText.text = dateViewText.format(calendar.time)
    }

    private fun setRainAmountIcon(rainAmountIcon: ImageView) {
        rainAmountIcon.setImageResource(R.mipmap.rain_amount)
    }

    private fun setRainAmountUnit(rainAmountUnit: TextView) {
        rainAmountUnit.text = Constants.RAIN_UNIT
    }

// =================================================================================================
//  ViewHolder
// =================================================================================================

    class ViewHolder(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding: ViewBinding = itemView
    }
}