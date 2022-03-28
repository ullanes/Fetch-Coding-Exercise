package com.example.fetchcodeex

import android.app.DownloadManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONArray
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val url = "https://fetch-hiring.s3.amazonaws.com/hiring.json"
    private var dataList = ArrayList<FetchEx>()
    private var jsonArray =JSONArray()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val queue = Volley.newRequestQueue(this)
        val request:StringRequest

        request =  StringRequest(Request.Method.GET, url ,
            Response.Listener { response ->
                val data = response.toString()
                jsonArray = JSONArray(data)
                jsonArray = MainMethods().removeBlankAndNoNames(jsonArray)
                dataList = MainMethods().addJsonInDataClass(jsonArray)
                dataList = MainMethods().sortDataClass(dataList)
                initializeLayoutManager(dataList)

            }, Response.ErrorListener { error ->  Log.e("Error", error.toString())})
         request.setTag("My tag")
        queue.add(request)
    }

    /*
    Populates a Recycler view with the cleaned and sorted data from json
    */

    private fun initializeLayoutManager(dataList: ArrayList<FetchEx> /* = java.util.ArrayList<com.example.fetchcodeex.FetchEx> */) {
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter()
        (adapter as RecyclerAdapter).setSize(dataList.size)
        recyclerView.adapter = adapter
    }

    /*
    Used for RecyclerAdapter to populateData
    */

    fun getData():ArrayList<FetchEx>{
        return dataList
    }





}


