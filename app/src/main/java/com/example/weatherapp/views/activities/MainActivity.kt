package com.example.weatherapp.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieDrawable
import com.example.weatherapp.R
import com.example.weatherapp.model.WeatherModelResponse
import com.example.weatherapp.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

// =================================================================================================
//  Attributes
// =================================================================================================

    val viewModel: WeatherViewModel by viewModels()

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
        spinner.setAnimation("loader.json")
        viewModel.init(0.0, 0.0)
    }

    private fun initView(data: WeatherModelResponse) {

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

