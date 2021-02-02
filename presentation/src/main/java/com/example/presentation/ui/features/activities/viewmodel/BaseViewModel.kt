package com.example.presentation.ui.features.activities.viewmodel

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.network.resource.Resource
import org.koin.core.KoinComponent

/**
 * Created by joseojalvo on 2020-08-05
 */
abstract class BaseViewModel : ViewModel(), KoinComponent {

// =================================================================================================
//  REST methods
// =================================================================================================

    /**
     * Method to be override in the ViewModel to call endpoints
     */
    abstract fun retrieveData(activity: AppCompatActivity)

// =================================================================================================
//  Protected methods
// =================================================================================================

    /**
     * Called to post the resource status when observe methods is finished
     * <T: Any> You can pass whatever you want in this method
     * @param resource the resource by any type
     * @param liveData the mutable live data that should modify
     */
    protected fun <T: Any> postResource(resource: Resource<T?>?, liveData: MutableLiveData<T?>) {
        resource?.let {
            when (resource.status) {
                Resource.Status.SUCCESS -> {
                    liveData.postValue(resource.data)
                }
                Resource.Status.DATA_NOT_AVAILABLE -> {
                    liveData.postValue(null)
                }
                Resource.Status.ERROR -> {
                    liveData.postValue(null)
                }
                Resource.Status.LOADING -> {
                    // Nothing to do
                }
            }
        }
    }
}