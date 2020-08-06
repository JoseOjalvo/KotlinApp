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

/**
 * Created by joseojalvo on 2020-08-06
 */
class WeatherAdapter(private var data: WeatherModelResponse) :
    RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

// =================================================================================================
//  Viewholder class
// =================================================================================================

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // =================================================================================================
        //  Views
        // =================================================================================================

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

        // =================================================================================================
        //  Attributes
        // =================================================================================================

        private fun setWeatherIcon(weather: String, weatherIcon: ImageView) {
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

            val parsedDate: String = date.removeRange(date.length - 3, date.length - 1)

            val year = parsedDate.substring(0, 4)
            val month = parsedDate.substring(4, 6)

            var day = parsedDate.substring(7, parsedDate.length)
            day = (day.toInt() + adapterPosition).toString()
            if (day.length == 1) {
                day = "0$day"
            }
            val finalDate = "$day/$month/$year"
            dateText.text = finalDate

        }
    }

// =================================================================================================
//  Recyclerview methods
// =================================================================================================

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.card_content, parent,
            false
        )

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.weatherDetailedListData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(data, position)
    }

}