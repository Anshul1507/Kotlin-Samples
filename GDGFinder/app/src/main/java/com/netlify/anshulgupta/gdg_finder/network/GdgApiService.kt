package com.netlify.anshulgupta.gdg_finder.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://developers-dot-devsite-v2-prod.appspot.com/community/gdg/groups/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface GdgApiService {
    @GET("directory.json")
    suspend fun getChapters() : GdgResponse
}

object GdgApi {
    val retrofitService : GdgApiService by lazy {
        retrofit.create(GdgApiService::class.java)
    }
}