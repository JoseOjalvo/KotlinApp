package com.example.weatherapp.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.Utils.Constants
import com.example.weatherapp.extensions.inflate
import com.example.weatherapp.model.WeatherModelResponse
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by joseojalvo on 2020-08-06
 */
class WeatherAdapter(private var data: WeatherModelResponse) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

// =================================================================================================
//  Viewholder class
// =================================================================================================

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val calendar = Calendar.getInstance()

        // =================================================================================================
        //  Views
        // =================================================================================================

        fun bindItem(data: WeatherModelResponse, position: Int) {
            val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIcon)
            val temperature: TextView = itemView.findViewById(R.id.temperature)
            val temperatureUnit: TextView = itemView.findViewById(R.id.temperature_unit)
            val rainAmount: TextView = itemView.findViewById(R.id.rainAmount)
            val rainAmountIcon: ImageView = itemView.findViewById(R.id.rainAmountIcon)
            val rainAmountUnit: TextView = itemView.findViewById(R.id.rainAmountUnit)
            val date: TextView = itemView.findViewById(R.id.date)

            setWeatherIcon(data.weatherDetailedListData[position].weather, weatherIcon)
            setRainAmount(data.weatherDetailedListData[position].prec_amount, rainAmount)
            setRainAmountIcon(rainAmountIcon)
            setRainAmountUnit(rainAmountUnit)
            setDate(data.initialDate, date)

            temperature.text = data.weatherDetailedListData[position].temp2m.toString()
            temperatureUnit.text = Constants.TEMPERATURE_UNIT
        }

        // =================================================================================================
        //  Attributes
        // =================================================================================================

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

        private fun setDate(date: String, dateText: TextView) {

            val dateParser = SimpleDateFormat(Constants.FORMAT_IN, Locale(Constants.REGION, Constants.COUNTRY))
            val dateFormatter = SimpleDateFormat(Constants.FORMAT_OUT, Locale(Constants.REGION, Constants.COUNTRY))

            val dateViewText = DateFormat.getDateInstance(
                DateFormat.MEDIUM,
                Locale(Constants.REGION, Constants.COUNTRY)
            )

            val parsedDate = dateFormatter.format(dateParser.parse(date)!!)
            calendar.time = dateFormatter.parse(parsedDate)!!
            calendar.add(Calendar.DATE, adapterPosition + 1)
            dateText.text = dateViewText.format(calendar.time)
        }

        private fun setRainAmountIcon(rainAmountIcon: ImageView) {
            rainAmountIcon.setImageResource(R.mipmap.rain_amount)
        }

        private fun setRainAmountUnit(rainAmountUnit: TextView) {
            rainAmountUnit.text = Constants.RAIN_UNIT
        }
    }

// =================================================================================================
//  Recyclerview methods
// =================================================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.card_content)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.weatherDetailedListData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data, position)
    }

}