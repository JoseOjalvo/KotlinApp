package com.example.weatherapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.core.KoinComponent

/**
 * Created by joseojalvo on 2020-08-05
 */
abstract class BaseViewModel : ViewModel(), KoinComponent {

// =================================================================================================
//  Attributes
// =================================================================================================

    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

// =================================================================================================
//  REST methods
// =================================================================================================

    abstract fun retrieveData()

// =================================================================================================
//  Private methods
// =================================================================================================

    protected fun showLoader() {
        isLoading.value = true
    }

    protected fun hideLoader() {
        isLoading.value = false
    }

}