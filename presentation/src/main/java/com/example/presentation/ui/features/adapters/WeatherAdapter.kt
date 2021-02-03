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
import com.example.commons.constants.Constants.RAIN_UNIT
import com.example.commons.constants.Constants.REGION
import com.example.commons.constants.Constants.RESOURCE_NOT_FOUND
import com.example.commons.constants.Constants.TEMPERATURE_UNIT
import com.example.data.model.WeatherModelResponse
import com.example.presentation.R
import com.example.presentation.databinding.CardContentBinding
import com.example.utils.date.DateUtils
import com.example.utils.weather.WeatherUtils
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
     * this should be used for variables which value depend on an injection, an init method or
     * if the initial value will be initialized before it's first use.
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
            holder.binding.temperature.text = data.dataseries?.get(position)?.temp2m.toString()
            holder.binding.temperatureUnit.text = TEMPERATURE_UNIT

            data.dataseries?.get(position)?.weather?.let {
                setWeatherIcon(it, holder.binding.weatherIcon)
            }
            data.dataseries?.get(position)?.precAmount?.let {
                setRainAmount(it, holder.binding.rainAmount)
            }
            data.init?.let {
                setDate(it, holder.binding.date, position)
            }
            setRainAmountIcon(holder.binding.rainAmountIcon)
            setRainAmountUnit(holder.binding.rainAmountUnit)
        }
    }

    /**
     * Returns the list of items to be displayed and the times the adapter will iterate to
     * show items
     */
    override fun getItemCount(): Int {
        data.dataseries?.size?.let {
            return it
        }
        return RESOURCE_NOT_FOUND
    }

// =================================================================================================
//  Private methods
// =================================================================================================

    /**
     * Establishes the weather icon based in the weather string received from the endpoint
     * @param weather key received from the endpoint
     */
    private fun setWeatherIcon(weather: String, weatherIcon: ImageView) {
        weatherIcon.setImageResource(WeatherUtils.getWeatherIcon(weather))
    }

    /**
     * Establishes the rain amount based in the rain amount received from the endpoint
     * @param rainAmount integer key received from the endpoint that points out the amount of rain
     * in a single day
     * @param rainAmountText view that'll prompt the rain amount value
     */
    private fun setRainAmount(rainAmount: Int, rainAmountText: TextView) {
        rainAmountText.text = WeatherUtils.getRainAmount(rainAmount)
    }

    /**
     * Sets the date the card component will show
     * @param date received from the endpoint, must be formatted and parsed
     * @param dateText view that will print the date
     * @param position position of the current component in the recyclerview
     */
    private fun setDate(date: String, dateText: TextView, position: Int) {
        dateText.text = DateUtils.setDate(date, true, position)
    }

    /**
     * Establishes the rain amount icon
     * @param rainAmountIcon image view that'll show the icon
     */
    private fun setRainAmountIcon(rainAmountIcon: ImageView) {
        rainAmountIcon.setImageResource(R.mipmap.rain_amount)
    }

    /**
     * Establishes the rain amount measure unit
     * @param rainAmountUnit view that'll show the rain amount unit
     */
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