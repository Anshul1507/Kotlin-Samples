package com.netlify.anshulgupta.marsrealestate.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MarsProperty(
    val id: String,
    @Json(name = "img_src")val imgSrcUrl: String,
    val type: String,
    val price: Double
):Parcelable{
    val isRental
    get() = type == "rent"
}
