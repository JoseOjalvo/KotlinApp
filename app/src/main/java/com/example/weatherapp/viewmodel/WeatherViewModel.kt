package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.weatherapp.model.WeatherModelQuery
import com.example.weatherapp.model.WeatherModelResponse
import com.example.weatherapp.usecase.WeatherUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.core.inject

/**
 * Created by joseojalvo on 2020-08-04
 */
open class WeatherViewModel : BaseViewModel() {

// =================================================================================================
//  Attributes
// =================================================================================================

    val processStatus: MutableLiveData<Boolean> = MutableLiveData()
    val useCase: WeatherUseCase by inject()
    val weatherData: MutableLiveData<WeatherModelResponse> by lazy {
        MutableLiveData<WeatherModelResponse>().also {
            retrieveData()
        }
    }

    private var queryModel: WeatherModelQuery = WeatherModelQuery()

// =================================================================================================
//  REST methods
// =================================================================================================

    override fun retrieveData() {

        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(
            useCase.execute(queryModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { showLoader() }
                .doOnError { hideLoader() }
                .subscribe({ response ->
                    onResponse(response)
                }, { t ->
                    onFailure(t)
                })
        )
    }

    fun onResponse(response: WeatherModelResponse) {
        hideLoader()

        weatherData.value = response
        processStatus.value = true
    }

    fun onFailure(t: Throwable) {
        hideLoader()
        processStatus.value = false
    }

// =================================================================================================
//  Initialization
// =================================================================================================


    /**
     * It will initialize the values of the model required to call the API service
     */
    fun init(longitude: Double, latitude: Double) {
        queryModel.longitude = longitude
        queryModel.latitude = latitude
    }


}