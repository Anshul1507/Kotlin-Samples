package tech.anshul1507.recyclergridview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var apiDataList = ArrayList<modelApi>()
    private var groupMap = hashMapOf<Int, List<Int>>()
    private var index: Int = 0

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

    private fun groupingData(list: ArrayList<modelApi>) {
        var defaultOld: Int = -1
        val tempList: ArrayList<Int> = ArrayList<Int>()
        tempList.clear()
        index = list[0].date.toString().substring(5, 6).toInt()
        for (i in list) {
            if (i.date.toString().substring(5, 6) == defaultOld.toString()) {

                tempList.add(i.score as Int)
            } else {

                if (defaultOld != -1) {
                    groupMap[defaultOld] = tempList.toList()
                    tempList.clear()
                }
                defaultOld = i.date.toString().substring(5, 6).toInt()
                tempList.add(i.score as Int)
            }
        }
        //adding last case in which we have left else loop
        if (tempList.isNotEmpty()) {
            groupMap[defaultOld] = tempList.toList()
        }
    }
}