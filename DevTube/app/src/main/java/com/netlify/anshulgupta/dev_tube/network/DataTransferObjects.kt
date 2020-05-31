package com.netlify.anshulgupta.dev_tube.network

import com.netlify.anshulgupta.dev_tube.ui.Video
import com.squareup.moshi.JsonClass


/**
 * Videos represent a devbyte that can be played.
 */
@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?
)

/**
 * Convert Network results to database objects
 */
public fun NetworkVideoContainer.asDomainModel():List<Video> {
    return videos.map {
        Video (
            title = it.title,
            description = it.description,
            url = it.url,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}

/**
 * VideoHolder holds a list of Videos.
 *
 * This is to parse first level of our network result which looks like
 *
 * {
 *   "videos": []
 * }
 */
@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)
