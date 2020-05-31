package com.netlify.anshulgupta.dev_tube.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VideoDao {
    @Query("SELECT * FROM dbvideo")
    fun getVideos() : List<DBVideo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DBVideo)
}