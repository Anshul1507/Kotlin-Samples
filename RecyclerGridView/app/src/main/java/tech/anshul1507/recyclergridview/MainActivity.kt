package tech.anshul1507.recyclergridview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var apiDataList = ArrayList<modelApi>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to add JSON data in list
        apiData()


    }

    private fun apiData() {
        //June
        apiDataList.add(modelApi("20200601", R.drawable.group_12871))
        //July
        apiDataList.add(modelApi("20200702", R.drawable.group_12871))
        apiDataList.add(modelApi("20200703", R.drawable.group_12871))
        apiDataList.add(modelApi("20200704", R.drawable.group_12871))
        apiDataList.add(modelApi("20200705", R.drawable.group_12871))
        apiDataList.add(modelApi("20200706", R.drawable.group_12871))
        apiDataList.add(modelApi("20200707", R.drawable.group_12871))
        apiDataList.add(modelApi("20200702", R.drawable.group_12871))
        apiDataList.add(modelApi("20200703", R.drawable.group_12871))
        apiDataList.add(modelApi("20200704", R.drawable.group_12871))
        apiDataList.add(modelApi("20200705", R.drawable.group_12871))
        apiDataList.add(modelApi("20200706", R.drawable.group_12871))
        apiDataList.add(modelApi("20200707", R.drawable.group_12871))
        apiDataList.add(modelApi("20200702", R.drawable.group_12871))
        apiDataList.add(modelApi("20200703", R.drawable.group_12871))
        apiDataList.add(modelApi("20200704", R.drawable.group_12871))
        apiDataList.add(modelApi("20200705", R.drawable.group_12871))
        apiDataList.add(modelApi("20200706", R.drawable.group_12871))
        apiDataList.add(modelApi("20200707", R.drawable.group_12871))
        //August
        apiDataList.add(modelApi("20200808", R.drawable.group_12871))
        apiDataList.add(modelApi("20200809", R.drawable.group_12871))
        apiDataList.add(modelApi("20200810", R.drawable.group_12871))
        apiDataList.add(modelApi("20200811", R.drawable.group_12871))
        apiDataList.add(modelApi("20200812", R.drawable.group_12871))
        apiDataList.add(modelApi("20200808", R.drawable.group_12871))
        apiDataList.add(modelApi("20200809", R.drawable.group_12871))
        apiDataList.add(modelApi("20200810", R.drawable.group_12871))
        apiDataList.add(modelApi("20200811", R.drawable.group_12871))
        apiDataList.add(modelApi("20200812", R.drawable.group_12871))
        apiDataList.add(modelApi("20200808", R.drawable.group_12871))
        apiDataList.add(modelApi("20200809", R.drawable.group_12871))
        apiDataList.add(modelApi("20200810", R.drawable.group_12871))
        apiDataList.add(modelApi("20200811", R.drawable.group_12871))
        apiDataList.add(modelApi("20200812", R.drawable.group_12871))
        //Sept
        apiDataList.add(modelApi("20200913", R.drawable.group_12871))
        apiDataList.add(modelApi("20200914", R.drawable.group_12871))
        apiDataList.add(modelApi("20200915", R.drawable.group_12871))
        apiDataList.add(modelApi("20200916", R.drawable.group_12871))
        apiDataList.add(modelApi("20200917", R.drawable.group_12871))
        apiDataList.add(modelApi("20200918", R.drawable.group_12871))
        apiDataList.add(modelApi("20200919", R.drawable.group_12871))
        apiDataList.add(modelApi("20200913", R.drawable.group_12871))
        apiDataList.add(modelApi("20200914", R.drawable.group_12871))
        apiDataList.add(modelApi("20200915", R.drawable.group_12871))
        apiDataList.add(modelApi("20200916", R.drawable.group_12871))
        apiDataList.add(modelApi("20200917", R.drawable.group_12871))
        apiDataList.add(modelApi("20200918", R.drawable.group_12871))
        apiDataList.add(modelApi("20200919", R.drawable.group_12871))
        apiDataList.add(modelApi("20200913", R.drawable.group_12871))
        apiDataList.add(modelApi("20200914", R.drawable.group_12871))
        apiDataList.add(modelApi("20200915", R.drawable.group_12871))
        apiDataList.add(modelApi("20200916", R.drawable.group_12871))
        apiDataList.add(modelApi("20200917", R.drawable.group_12871))
        apiDataList.add(modelApi("20200918", R.drawable.group_12871))
        apiDataList.add(modelApi("20200919", R.drawable.group_12871))

    }

}