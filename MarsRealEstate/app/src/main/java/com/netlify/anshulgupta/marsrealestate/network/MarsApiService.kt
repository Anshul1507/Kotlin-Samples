package com.netlify.anshulgupta.marsrealestate.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://mars.udacity.com/"

enum class MarsApiFilter(val value:String){
    SHOW_RENT("rent"),
    SHOW_BUY("buy"),
    SHOW_ALL("all")
}

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MarsApiService {
    @GET("realestate")
    suspend fun getPropertiesAsync(@Query("filter") type:String): List<MarsProperty>
}

object MarsApi{
    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}
