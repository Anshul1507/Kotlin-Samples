package com.netlify.anshulgupta.dev_tube.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.netlify.anshulgupta.dev_tube.ui.Video


/* Creating a room Entity*/
@Entity
data class DBVideo constructor(
    @PrimaryKey val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String
)

/* Function -> converts from DB objects to domain objects */
fun List<DBVideo>.asDomainModel(): List<Video> {
    return map {
        Video(
            url = it.url,
            title =  it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}