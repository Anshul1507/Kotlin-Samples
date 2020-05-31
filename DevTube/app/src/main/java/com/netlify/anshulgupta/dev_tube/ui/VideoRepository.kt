package com.netlify.anshulgupta.dev_tube.ui

import com.netlify.anshulgupta.dev_tube.db.VideoDB
import com.netlify.anshulgupta.dev_tube.network.Network
import com.netlify.anshulgupta.dev_tube.network.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/* Repository for fetching videos from the network and storing them on disk.*/
class VideoRepository (private val db:VideoDB){
    suspend fun refreshVideos(){
        withContext(Dispatchers.IO){
            val playlist = Network.devTube.getPlayList()
            db.videoDao.insertAll(*playlist.asDataBaseModel()) //storing into db
        }
    }
}