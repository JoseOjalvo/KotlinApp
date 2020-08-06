package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.Utils.Constants
import com.example.weatherapp.model.WeatherModelResponse
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Created by joseojalvo on 2020-08-06
 */
class WeatherAdapter(var data: WeatherModelResponse) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItem(data: WeatherModelResponse, position: Int) {
            val weatherIcon: ImageView = itemView.findViewById(R.id.weatherIcon)
            val temperature: TextView = itemView.findViewById(R.id.temperature)
            val temperatureUnit: TextView = itemView.findViewById(R.id.temperature_unit)
            val rainAmount: TextView = itemView.findViewById(R.id.rainAmount)
            val date: TextView = itemView.findViewById(R.id.date)

            setWeatherIcon(data.weatherDetailedListData[position].weather, weatherIcon)
            setRainAmount(data.weatherDetailedListData[position].prec_amount, rainAmount)
            setDate(data.initialDate, date)

            temperature.text = data.weatherDetailedListData[position].temp2m.toString()
            temperatureUnit.text = Constants.TEMPERATURE_UNIT

        }

        fun setWeatherIcon(weather: String, weatherIcon: ImageView) {
            when (weather) {
                "clearday",
                "clearnight" -> weatherIcon.setImageResource(R.mipmap.sun)

                "humidday",
                "humidnight",
                "pcloudyday",
                "pcloudynight" -> weatherIcon.setImageResource(R.mipmap.partly_cloudy)

                "mcloudyday",
                "mcloudynight",
                "cloudyday",
                "cloudynight" -> weatherIcon.setImageResource(R.mipmap.clouds)

                "lightrainday",
                "lightrainnight",
                "oshowerday",
                "oshowernight",
                "ishowerday",
                "ishowernight",
                "rainday",
                "rainnight" -> weatherIcon.setImageResource(R.mipmap.rain)

                "snowday",
                "snownight",
                "rainsnowday",
                "rainsnownight" -> weatherIcon.setImageResource(R.mipmap.snow)
            }
        }

        fun setRainAmount(rainAmount: Int, rainAmountText: TextView) {
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

        fun setDate(date: String, dateText: TextView) {

            var parsedDate: String = date.removeRange(date.length - 3, date.length - 1)

            var year = parsedDate.substring(0, 3)
            var month = parsedDate.substring(4, 6)
            var day = parsedDate.substring(7, parsedDate.length - 1)

            var finalDate = (day.toInt() + adapterPosition).toString() + "-" + month + "-" + year
            dateText.text = finalDate

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_content, parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.weatherDetailedListData.size
    }

    override fun onBindViewHolder(holder: WeatherAdapter.ViewHolder, position: Int) {
        holder.bindItem(data, position)
    }

}