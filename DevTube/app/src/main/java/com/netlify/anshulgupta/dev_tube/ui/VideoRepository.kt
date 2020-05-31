package com.netlify.anshulgupta.dev_tube.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.netlify.anshulgupta.dev_tube.db.VideoDB
import com.netlify.anshulgupta.dev_tube.db.asDomainModel
import com.netlify.anshulgupta.dev_tube.network.Network
import com.netlify.anshulgupta.dev_tube.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/* Repository for fetching videos from the network and storing them on disk.*/
class VideoRepository (private val db:VideoDB){

    /* Playlist of videos that can be shown on the screen
    * Transformations.map used for converting one Live data to another form
    * */
    val videos:LiveData<List<Video>> = Transformations.map(db.videoDao.getVideos()){
        it.asDomainModel()
    }

    /* Refresh the videos stored in the offline cache.
    *  By using IO dispatcher here, we are free to call this from any thread including the main thread.
    * */
    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val playlist = Network.devTube.getPlayList()
            db.videoDao.insertAll(*playlist.asDataBaseModel()) //storing into db
        }
    }
}