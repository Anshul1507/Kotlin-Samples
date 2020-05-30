package com.netlify.anshulgupta.retrofit_demo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import com.netlify.anshulgupta.retrofit_demo.DataClasses.User
import com.netlify.anshulgupta.retrofit_demo.DataClasses.UserItem
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val callAPI= UserAPI.retrofitService.getAllUsers()

        callAPI.enqueue(object : Callback<List<UserItem>> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: retrofit2.Call<List<UserItem>>, t: Throwable) {
                text_data.text = "Failure : ${t.message}"
            }

            override fun onResponse(call: retrofit2.Call<List<UserItem>>,response: Response<List<UserItem>>) {
                text_data.text = response.body().toString()
            }
        })

    }
}
