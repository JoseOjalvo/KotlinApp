package com.example.presentation.ui.features.activities.view

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.commons.constants.Constants.COUNTRY
import com.example.commons.constants.Constants.EMPTY_STRING
import com.example.commons.constants.Constants.FIRST_POSITION
import com.example.commons.constants.Constants.FORMAT_IN
import com.example.commons.constants.Constants.FORMAT_OUT
import com.example.commons.constants.Constants.LATITUDE
import com.example.commons.constants.Constants.LOADER_NAME
import com.example.commons.constants.Constants.LONGITUDE
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
import com.example.commons.constants.Constants.REGION
import com.example.commons.constants.Constants.clearDay
import com.example.commons.constants.Constants.cloudDay
import com.example.commons.constants.Constants.cloudyDay
import com.example.commons.constants.Constants.rainDay
import com.example.commons.constants.Constants.snowDay
import com.example.data.model.WeatherModelResponse
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.ui.features.activities.viewmodel.WeatherViewModel
import com.example.presentation.ui.features.adapters.WeatherAdapter
import com.example.utils.extensions.gone
import com.example.utils.extensions.visible
import com.example.utils.permission.PermissionUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding>() {

// =================================================================================================
//  Attributes
// =================================================================================================

    /**
     * It contains all the endpoint related features and should implement the logic non related to
     * the view of a Fragment/Activity as it's stated in the MVVM design pattern
     */
    private val viewModel: WeatherViewModel by viewModel()

// =================================================================================================
//  Config
// =================================================================================================

    /**
     * Creates a view binding instance with the layout of the current activity
     * ViewBinding is generated when the project is compiled and the name of the class
     * is generated using the XML file name, if the XML is fragment_card_list
     * the ViewBinding class will be FragmentCardList.
     *
     * This should be always used in Activities, Fragments and Adapters, in custom components
     * findViewById() should be used.
     */
    override fun setBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        observeData()
        requestPermission()
    }

    /**
     * Callback triggered after requesting permissions
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        PermissionUtils.checkPermission(requestCode, grantResults, this, this,
        binding.container, Manifest.permission.ACCESS_COARSE_LOCATION) {
            requestPermission()
        }
    }

// =================================================================================================
//  Observer
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
//  Private methods
// =================================================================================================

    /**
     * It initializes the views and data and calls the weather endpoint
     */
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
     * Requests a permission to geolocate the user
     */
    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), 0
        )
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
        binding.banner.temperature?.text =
            data.weatherDetailedListData[FIRST_POSITION].temp2m.toString()
    }

    /**
     * It checks if the lists contains the weather string provided by the endpoint to set the
     * weather icon to the card component
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
        /**
         * When expressions are the kotlin equivalent to a switch in java, they use a lambda
         * expression type to refer to the possible values to handle
         */
        binding.banner.rainAmountText?.let {
            it.text = when (rainAmount) {
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

    /**
     * Parses the date and prints it
     */
    private fun setDate(date: String) {
        val dateParser = SimpleDateFormat(FORMAT_IN, Locale(REGION, COUNTRY))
        val dateFormatter = SimpleDateFormat(FORMAT_OUT, Locale(REGION, COUNTRY))
        val dateViewText = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale(REGION, COUNTRY))
        var parsedDate = EMPTY_STRING

        /**
         * Let or safe call works the same as != null, but it returns the value of the variable
         * that was contained at the moment, !! should never be used because it throws an exception
         * if the value is null meanwhile a safe call doesn't, the code below won't be executed
         */
        dateParser.parse(date)?.let {
            parsedDate = dateFormatter.format(it)
        }
        dateFormatter.parse(parsedDate)?.let {
            binding.banner.dateText?.text = dateViewText.format(it)
        }
    }

}

