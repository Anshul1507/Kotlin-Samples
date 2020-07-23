package tech.anshul1507.retrofitpost

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIService {

    @POST("api/login")
    fun sendUserData(@Body userPosts: Model): Call<Model>

}