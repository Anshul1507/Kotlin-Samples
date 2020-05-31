package com.netlify.anshulgupta.dev_tube.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface VideoDao {
    @Query("SELECT * FROM dbvideo")
    fun getVideos() : LiveData<List<DBVideo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DBVideo)
}

@Database(entities = [DBVideo::class], version = 1)
abstract class VideoDB: RoomDatabase() {
    abstract val videoDao: VideoDao
}

private lateinit var INSTANCE: VideoDB

fun getDB(context: Context): VideoDB {

    if(!::INSTANCE.isInitialized){
        //when instance is not initialized -> create
        INSTANCE = Room.databaseBuilder(context.applicationContext,VideoDB::class.java,"videos").build()
    }

    return INSTANCE
}