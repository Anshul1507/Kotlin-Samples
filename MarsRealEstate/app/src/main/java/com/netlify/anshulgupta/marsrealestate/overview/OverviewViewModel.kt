package com.netlify.anshulgupta.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netlify.anshulgupta.marsrealestate.network.MarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    //For working with co-routines -> Need to create a job and then use that job with co-routine scope in terms of dispatcher
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {

            val getPropertiesDeferred = MarsApi.retrofitService.getPropertiesAsync()
            try {
                val listResult = getPropertiesDeferred
                _response.value = "Success: ${listResult.size} Mars properties retrieved!"
            }catch (t: Throwable){
                _response.value = "Failure: " + t.message
            }

        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
