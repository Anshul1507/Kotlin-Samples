package tech.anshul1507.retrofitpost

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login.setOnClickListener {
            val email = email_tv.text.toString()
            val password = password_tv.text.toString()

            val userLogin = Model(email,password)
            val call = LoginAPI.retrofitService.sendUserData(userLogin)

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