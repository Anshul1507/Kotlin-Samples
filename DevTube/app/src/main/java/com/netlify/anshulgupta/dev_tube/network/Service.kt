package com.netlify.anshulgupta.dev_tube.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//If add more services, make sure to share the retrofit object between services

// https://devbytes.udacity.com/  +  devbytes.json

private const val BASE_URL = "https://devbytes.udacity.com/"
/**
 * Moshi Converter Factory
 * */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Main Entry point for network access.
 * */
object Network {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
    val devTube = retrofit.create(DevTubeService::class.java)
}

/**
 * A retrofit service to fetch a devbyte playlist
 * */
interface DevTubeService {
    @GET("devbytes.json")
     suspend fun getPlayList(): NetworkVideoContainer
}