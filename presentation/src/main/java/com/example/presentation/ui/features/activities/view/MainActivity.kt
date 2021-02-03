package com.example.presentation.ui.features.activities.view

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.commons.constants.Constants.FIRST_POSITION
import com.example.commons.constants.Constants.LOADER_NAME
import com.example.commons.constants.Constants.RESOURCE_NOT_FOUND
import com.example.data.model.WeatherModelResponse
import com.example.presentation.databinding.ActivityMainBinding
import com.example.presentation.ui.features.activities.viewmodel.WeatherViewModel
import com.example.presentation.ui.features.adapters.WeatherAdapter
import com.example.utils.date.DateUtils
import com.example.commons.extensions.gone
import com.example.commons.extensions.visible
import com.example.utils.permission.PermissionUtils
import com.example.utils.weather.WeatherUtils
import org.koin.androidx.viewmodel.ext.android.viewModel
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
     * All layouts that use view binding must have a root element of type <layout> to work
     *
     * This should be always used in Activities, Fragments and Adapters, in custom components
     * findViewById() should be used.
     *
     * The layout name convention is:
     * 1.- Activities -> activity_example_view
     * 2.- Fragments -> fragment_example_view
     * 3.- Custom components -> custom_example_view
     *
     * @param inflater LayoutInflater that allows us to create the view
     * @return the view binding containing the layout of the current activity
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
     * @param data received from the weather endpoint
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

    /**
     * Initialize the banner with [WeatherModelResponse]
     * data retrieved from service
     * @param data received from the weather endpoint
     */
    private fun initBanner(data: WeatherModelResponse) {
        binding.banner.init()
        initBannerComponent(data)
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
     * @param data received from the weather endpoint
     */
    private fun initBannerComponent(data: WeatherModelResponse) {
        data.dataseries?.get(FIRST_POSITION)?.weather?.let {
            setWeatherIcon(it)
        }
        data.dataseries?.get(FIRST_POSITION)?.precAmount?.let {
            setRainAmount(it)
        }
        data.init?.let {
            binding.banner.dateText?.text = DateUtils.setDate(
                it, false, RESOURCE_NOT_FOUND
            )
        }
        binding.banner.temperature?.text =
            data.dataseries?.get(FIRST_POSITION)?.temp2m.toString()
    }

    /**
     * Establishes the weather icon based in the weather string received from the endpoint
     * @param weather key received from the endpoint
     */
    private fun setWeatherIcon(weather: String) {
        binding.banner.weatherIcon?.setImageResource(WeatherUtils.getWeatherIcon(weather))
    }

    /**
     * Called to set rain amount data
     * @param rainAmount amount of rain specified by the endpoint
     */
    private fun setRainAmount(rainAmount: Int) {
        binding.banner.rainAmountText?.let {
            it.text = WeatherUtils.getRainAmount(rainAmount)
        }
    }
}

