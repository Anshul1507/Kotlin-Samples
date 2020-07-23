package tech.anshul1507.retrofitpost

import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

private const val BASE_URL = "https://reqres.in/"

private val client = OkHttpClient.Builder().build()

private val retrofitBuilder = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl("https://reqres.in/")
    .client(client)
    .build()

interface APIService {
    @POST("api/login")
    fun sendUserData(@Body userLogin: Model): Call<Model>
}

object LoginAPI{
    val retrofitService: APIService by lazy {
        retrofitBuilder.create(APIService::class.java)
    }
}