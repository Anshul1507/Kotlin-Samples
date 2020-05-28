package com.netlify.anshulgupta.marsrealestate.network

import com.squareup.moshi.Json

data class MarsProperty(
    val id: String,
    @Json(name = "img_src")val imgSrcUrl: String,
    val type: String,
    val price: Double
)
