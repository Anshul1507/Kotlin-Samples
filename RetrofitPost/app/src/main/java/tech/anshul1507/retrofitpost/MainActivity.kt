package tech.anshul1507.retrofitpost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val client = OkHttpClient.Builder().build()
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://reqres.in/")
            .client(client)
            .build()

        val apiService = retrofitBuilder.create(APIService::class.java)

        login.setOnClickListener {
            val email = email_tv.text.toString()
            val password = password_tv.text.toString()

            val userLogin = Model(email,password)
            val call = apiService.sendUserData(userLogin)

            call.enqueue(object : Callback<Model> {
                override fun onFailure(call: Call<Model>, t: Throwable) {
                    Toast.makeText(applicationContext,"Login Failure",Toast.LENGTH_SHORT).show()
                    Log.i("Failure",t.message.toString())
                }

                override fun onResponse(call: Call<Model>, response: Response<Model>) {
                    Toast.makeText(applicationContext,"Login Success",Toast.LENGTH_SHORT).show()
                    Log.i("Code",response.code().toString())
                }
            })
        }

    }
}