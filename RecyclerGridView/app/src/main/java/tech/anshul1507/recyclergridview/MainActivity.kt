package tech.anshul1507.recyclergridview

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.ListAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.item_main.view.*

class MainActivity : AppCompatActivity() {

    private var apiDataList = ArrayList<modelApi>()
    private var groupMap = hashMapOf<String, List<Int>>()
    private lateinit var index: String
    private var indexCounterList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //to add JSON data in list
        apiData()
        //to grouping apiDataList into HashMaps
        groupingData(apiDataList)

        //setting data into grid-recycler view
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = RecyclerAdapter(indexCounterList, this, groupMap)
        recycler_view.isNestedScrollingEnabled = false
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

        apiDataList.add(modelApi("20210918", R.drawable.group_12871))
        apiDataList.add(modelApi("20210919", R.drawable.group_12871))
        apiDataList.add(modelApi("20210913", R.drawable.group_12871))
        apiDataList.add(modelApi("20210914", R.drawable.group_12871))
        apiDataList.add(modelApi("20210915", R.drawable.group_12871))

        apiDataList.add(modelApi("20220916", R.drawable.group_12871))
        apiDataList.add(modelApi("20220917", R.drawable.group_12871))
        apiDataList.add(modelApi("20220918", R.drawable.group_12871))
        apiDataList.add(modelApi("20220919", R.drawable.group_12871))

    }

    private fun groupingData(list: ArrayList<modelApi>) {
        var defaultOld = ""
        val tempList: ArrayList<Int> = ArrayList<Int>()
        tempList.clear()
        index = list[0].date.toString().substring(0, 6) //index -> "202007" [2020->year & 07->month]
        for (i in list) {
            if (i.date.toString().substring(0, 6) == defaultOld) {

                tempList.add(i.score as Int)
            } else {

                if (defaultOld.isNotEmpty()) {
                    indexCounterList.add(defaultOld)
                    groupMap[defaultOld] = tempList.toList()
                    tempList.clear()
                }
                defaultOld = i.date.toString().substring(0, 6)
                tempList.add(i.score as Int)
            }
        }
        //adding last case in which we have left else loop
        if (tempList.isNotEmpty()) {
            indexCounterList.add(defaultOld)
            groupMap[defaultOld] = tempList.toList()
            tempList.clear()
        }
    }

    class GridAdapter(mContext: Context, mList: List<Int>) : BaseAdapter() {
        private var list = mList
        private var context: Context? = mContext

        override fun getCount(): Int {
            return list.size
        }

        @SuppressLint("InflateParams", "ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val itemGrid = this.list[position]
            val inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.item, null, false)

            view.imgItem.setImageResource(itemGrid)

            return view
        }

        override fun getItem(p0: Int): Any {
            return list[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }
    }

    class RecyclerAdapter(idxList : ArrayList<String>, mContext: Context, hashList: HashMap<String, List<Int>>) :
        RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
        private var list = hashList
        private var mIdx = idxList
        private var context: Context? = mContext

        class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            companion object {
                //layout objects/class objects/keys
            }
        }

        @SuppressLint("InflateParams")
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflater.inflate(R.layout.item_main, null, false)

            return MyViewHolder(view)
        }

        override fun getItemCount() = list.size

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//            val monthStart = mIdx.substring(5,6)
//            val yearStart = mIdx.substring(0,4)

            val item = list[mIdx[position]] as List<Int>
            val monthsList = mutableListOf ("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec")
            context?.let { GridAdapter(it, item) }?.let {
                setGridViewHeightBasedOnChildren(
                    holder.itemView.gridview, 6,
                    it
                )
            }
            holder.itemView.text_month.text = monthsList[mIdx[position].substring(5,6).toInt()]
            holder.itemView.text_year.text = mIdx[position].substring(0,4)
            holder.itemView.gridview.adapter = context?.let { GridAdapter(it, item) }

        }

        private fun setGridViewHeightBasedOnChildren(
            gridView: GridView,
            noOfColumns: Int,
            gridAdapter: ListAdapter
        ) {
            val gridViewAdapter = gridAdapter
                ?: // adapter is not set yet
                return
            var totalHeight: Int //total height to set on grid view
            val items = gridViewAdapter.count //no. of items in the grid
            val rows: Int //no. of rows in grid
            val listItem = gridViewAdapter.getView(0, null, gridView)
            listItem.measure(0, 0)
            totalHeight = listItem.measuredHeight
            val x: Float
            if (items > noOfColumns) {
                x = items / noOfColumns.toFloat()

                //Check if exact no. of rows of rows are available, if not adding 1 extra row
                rows = if (items % noOfColumns != 0) {
                    (x + 1).toInt()
                } else {
                    x.toInt()
                }
                totalHeight *= rows

                //Adding any vertical space set on grid view
                totalHeight += gridView.verticalSpacing * rows
            }

            //Setting height on grid view
            val params = gridView.layoutParams
            params.height = totalHeight
            gridView.layoutParams = params
        }
    }
}