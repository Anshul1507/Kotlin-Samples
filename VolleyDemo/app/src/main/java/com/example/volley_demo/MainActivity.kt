package com.example.volley_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),IVolley {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_get.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .getRequest("https://jsonplaceholder.typicode.com/todos/1")
        }

        btn_post.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .postRequest("https://jsonplaceholder.typicode.com/posts")
        }

        btn_put.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .putRequest("https://jsonplaceholder.typicode.com/posts/1")
        }

        btn_patch.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity,this@MainActivity)
                .patchRequest("https://jsonplaceholder.typicode.com/posts/1")
        }

        btn_delete.setOnClickListener {
            MyVolleyRequest.getInstance(this@MainActivity, this@MainActivity)
                .deleteRequest("https://jsonplaceholder.typicode.com/posts/1")
        }

        btn_image_loader.setOnClickListener {
            image_view.setImageUrl("https://cdn.searchenginejournal.com/wp-content/uploads/2019/09/f46bc1a6-9ffa-4434-a77a-ea1491d3597a-760x400.jpeg",
            MyVolleyRequest.getInstance(this@MainActivity).imageLoader)
        }
    }


    override fun onResponse(response: String) {
        Toast.makeText(this@MainActivity,""+response,Toast.LENGTH_SHORT).show()
    }
}


