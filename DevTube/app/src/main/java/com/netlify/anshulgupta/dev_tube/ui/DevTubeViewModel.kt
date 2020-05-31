package com.netlify.anshulgupta.dev_tube.ui

import android.app.Application
import androidx.lifecycle.*
import com.netlify.anshulgupta.dev_tube.network.Network
import com.netlify.anshulgupta.dev_tube.network.asDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class DevTubeViewModel(application: Application) : AndroidViewModel(application) {

    /* This is the Job for all co-routines started by this ViewModel - Cancelling this job will cancel all co-routines started by this ViewModel*/
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _playlist = MutableLiveData<List<Video>>()
    val playlist: LiveData<List<Video>>
        get() = _playlist

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {
        try {
            val playlist = Network.devTube.getPlayList()
            _playlist.postValue(playlist.asDomainModel())
        }catch (networkError : IOException){
            // Show an infinite loading spinner if the request fails
            // challenge exercise: show an error to the user if the network request fails
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DevTubeViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DevTubeViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}