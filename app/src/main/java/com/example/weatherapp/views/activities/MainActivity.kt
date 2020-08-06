package com.example.weatherapp.views.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieDrawable
import com.example.weatherapp.R
import com.example.weatherapp.Utils.Constants
import com.example.weatherapp.adapters.WeatherAdapter
import com.example.weatherapp.model.WeatherModelResponse
import com.example.weatherapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

// =================================================================================================
//  Attributes
// =================================================================================================

    private val viewModel: WeatherViewModel by viewModels()

// =================================================================================================
//  Config methods
// =================================================================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        observeData()
    }

// =================================================================================================
//  Initialization methods
// =================================================================================================

    private fun init() {
        spinner.setAnimation(Constants.LOADER_NAME)
        viewModel.init(Constants.LONGITUDE, Constants.LATITUDE)
    }

    private fun initView(data: WeatherModelResponse) {
        startListView(data)
    }

    /**
     * Initialize the RecyclerView with [WeatherModelResponse]
     * data retrieved from service
     */
    private fun startListView(data: WeatherModelResponse) {

        with(weatherList) {
            layoutManager = LinearLayoutManager(
                this@MainActivity,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = WeatherAdapter(data)
        }
    }

// =================================================================================================
//  MVVM Observer
// =================================================================================================

    private fun observeData() {
        viewModel.isLoading.observe(this, Observer { load -> setLoadingStatus(load) })
        viewModel.weatherData.observe(this, Observer { data -> initView(data) })
    }

// =================================================================================================
//  Loader methods
// =================================================================================================

    private fun setLoadingStatus(isLoading: Boolean) {
        if (isLoading) {
            showLoad()
        } else {
            hideLoad()
        }
    }

    private fun showLoad() {
        spinner.visibility = View.VISIBLE
        spinner.playAnimation()
        spinner.repeatCount = LottieDrawable.INFINITE
    }

    private fun hideLoad() {
        spinner.repeatCount = 0
        spinner.visibility = View.GONE
    }

}

