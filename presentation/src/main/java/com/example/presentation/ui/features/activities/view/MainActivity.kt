package com.example.presentation.ui.features.activities.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.commons.constants.Constants
import com.example.commons.constants.Constants.FIRST_POSITION
import com.example.commons.constants.Constants.LATITUDE
import com.example.commons.constants.Constants.LOADER_NAME
import com.example.commons.constants.Constants.LONGITUDE
import com.example.commons.constants.Constants.clearDay
import com.example.commons.constants.Constants.cloudDay
import com.example.commons.constants.Constants.cloudyDay
import com.example.commons.constants.Constants.rainDay
import com.example.commons.constants.Constants.snowDay
import com.example.data.model.WeatherModelResponse
import com.example.presentation.R
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.ui.features.activities.viewmodel.WeatherViewModel
import com.example.presentation.ui.features.adapters.WeatherAdapter
import com.example.utils.extensions.gone
import com.example.utils.extensions.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding>() {

// =================================================================================================
//  Attributes
// =================================================================================================

    private val viewModel: WeatherViewModel by viewModel()

// =================================================================================================
//  Config methods
// =================================================================================================

    /**
     * Creates a view binding instance with the layout of the current activity
     */
    override fun setBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeData()
    }

// =================================================================================================
//  Initialization methods
// =================================================================================================

    private fun init() {
        binding.spinner.setAnimation(LOADER_NAME)
        viewModel.queryModel.longitude = LONGITUDE
        viewModel.queryModel.latitude = LATITUDE
        showLoad()
        viewModel.retrieveData(this)
    }

    private fun initView(data: WeatherModelResponse) {
        initBanner(data)
        startListView(data)
    }

    /**
     * Initialize the RecyclerView with [WeatherModelResponse]
     * data retrieved from service
     */
    private fun startListView(data: WeatherModelResponse) {
        with(binding.weatherList) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = WeatherAdapter(data)
        }
    }

    private fun initBanner(data: WeatherModelResponse) {
        binding.banner.init()
        initList(data)
    }

// =================================================================================================
//  MVVM Observer
// =================================================================================================

    /**
     * Observes the data received from the ViewModel, if a change is notified the observer will be
     * triggered
     */
    private fun observeData() {
        viewModel.data.observe(this, { result ->
            hideLoad()
            result?.let { search ->
                initView(search)
            }
        })
    }

// =================================================================================================
//  Loader methods
// =================================================================================================

    /**
     * Makes the loader appear
     */
    private fun showLoad() {
        binding.spinner.visible()
        binding.spinner.playAnimation()
        binding.spinner.repeatCount = LottieDrawable.INFINITE
    }

    /**
     * Makes the loader disappear
     */
    private fun hideLoad() {
        binding.spinner.repeatCount = 0
        binding.spinner.gone()
    }

    /**
     * Initializes the list component with data
     */
    private fun initList(data: WeatherModelResponse) {
        setWeatherIcon(data.weatherDetailedListData[FIRST_POSITION].weather)
        setRainAmount(data.weatherDetailedListData[FIRST_POSITION].prec_amount)
        setDate(data.initialDate)
        binding.banner.temperature?.text = data.weatherDetailedListData[Constants.FIRST_POSITION].temp2m.toString()
    }

    /**
     * Called to set weather icons
     */
    private fun setWeatherIcon(weather: String) {
        when {
            clearDay.contains(weather) -> binding.banner.weatherIcon
                ?.setImageResource(com.example.commons.R.mipmap.sun)
            rainDay.contains(weather) -> binding.banner.weatherIcon
                ?.setImageResource(com.example.commons.R.mipmap.rain)
            snowDay.contains(weather) -> binding.banner.weatherIcon
                ?.setImageResource(com.example.commons.R.mipmap.snow)
            cloudDay.contains(weather) -> binding.banner.weatherIcon
                ?.setImageResource(com.example.commons.R.mipmap.clouds)
            cloudyDay.contains(weather) -> binding.banner.weatherIcon
                ?.setImageResource(com.example.commons.R.mipmap.partly_cloudy)
        }
    }

    /**
     * Called to set rain amount data
     */
    private fun setRainAmount(rainAmount: Int) {
        when (rainAmount) {
            0 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_0
            1 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_1
            2 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_2
            3 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_3
            4 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_4
            5 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_5
            6 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_6
            7 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_7
            8 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_8
            9 -> binding.banner.rainAmountText?.text = Constants.RAIN_AMOUNT_9
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
        binding.banner.dateText?.text = dateViewText.format(dateFormatter.parse(parsedDate)!!)
    }

}

