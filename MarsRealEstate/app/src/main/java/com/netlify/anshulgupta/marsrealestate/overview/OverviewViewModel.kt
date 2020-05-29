package com.netlify.anshulgupta.marsrealestate.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netlify.anshulgupta.marsrealestate.network.MarsApi
import com.netlify.anshulgupta.marsrealestate.network.MarsProperty
import kotlinx.coroutines.*
import java.util.ArrayList

enum class MarsApiStatus { LOADING, FAILURE, SUCCESS }
class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()

    val status: LiveData<MarsApiStatus>
        get() = _status

    //Adding DataClass externally with MutableLiveData for accessing sub-objects
    private val _properties = MutableLiveData<List<MarsProperty>>()

    val properties: LiveData<List<MarsProperty>>
        get() = _properties

    //For start detail fragment Navigation
    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty

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
                _status.value = MarsApiStatus.LOADING
                val listResult = (getPropertiesDeferred)
                _status.value = MarsApiStatus.SUCCESS
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                }
            } catch (e: Exception) {
                _status.value = MarsApiStatus.FAILURE
                _properties.value =
                    ArrayList() //setting empty array list so that recyclerView has zero size
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayPropertyDetails(marsProperty: MarsProperty){
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete(){
        _navigateToSelectedProperty.value = null
    }
}
