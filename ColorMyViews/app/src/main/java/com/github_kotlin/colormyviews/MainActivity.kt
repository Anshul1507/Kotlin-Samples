package com.github_kotlin.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
    }

    private fun setListeners() {
        val clickableViews: List<View> =
            listOf(tv1,tv2,tv3,tv4,tv5,constraintLayout,red_button,yellow_button,green_button)

        for(items in clickableViews){
            items.setOnClickListener {
                makeColored(it)
            }
        }
    }

    private fun makeColored(view :View) {
        when(view.id){

            R.id.tv1 -> view.setBackgroundColor(Color.DKGRAY)
            R.id.tv2 -> view.setBackgroundColor(Color.GRAY)

            R.id.tv3 -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.tv4 -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.tv5 -> view.setBackgroundResource(android.R.color.holo_green_light)

            R.id.red_button -> tv3.setBackgroundResource(R.color.red)
            R.id.yellow_button -> tv4.setBackgroundResource(R.color.yellow)
            R.id.green_button -> tv5.setBackgroundResource(R.color.green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}
