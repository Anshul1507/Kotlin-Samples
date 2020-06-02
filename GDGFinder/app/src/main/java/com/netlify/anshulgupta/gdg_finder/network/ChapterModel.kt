package com.netlify.anshulgupta.gdg_finder.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GdgResponse(
    @Json(name = "filters_") val filters: Filter,
    @Json(name = "data") val chapters: List<ChapterModel>
) : Parcelable

@Parcelize
data class Filter(
    @Json(name = "region") val regions: List<String>
) : Parcelable

@Parcelize
data class ChapterModel(
    @Json(name = "chapter_name") val name: String,
    @Json(name = "cityarea") val city: String,
    val country: String,
    val region: String,
    val website: String,
    val geo: LatLong
) : Parcelable

@Parcelize
data class LatLong(
    val latitude: Double,
    @Json(name = "lngc")
    val longitude: Double
) : Parcelable

