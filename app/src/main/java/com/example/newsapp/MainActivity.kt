package com.example.newsapp

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerViewData = ArrayList<NasaData>()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val okHttpClient = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.nasa.gov/planetary/apod/?api_key=opuhhMHXqz63ocDq9C2kljzs9nU7l1W1qEXzuqhQ&start_date=2022-03-01")
            .build()
        okHttpClient.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                val res = response.body!!.string()
                this@MainActivity.runOnUiThread(Runnable() {
                    val jsonArray = JSONArray(res)
                    for (i in 0 until jsonArray.length()) {
                        try {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val author = jsonObject.getString("copyright")
                            val title = jsonObject.getString("title")
                            val description = jsonObject.getString("explanation")
                            val date = jsonObject.getString("date")
                            val imageUrl = jsonObject.getString("url")
                            recyclerViewData.add(NasaData(title, author, date.toString(), description, imageUrl))
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                    recyclerView.adapter = Adapter(recyclerView, this@MainActivity, recyclerViewData, this@MainActivity)
                })
            }
        })

    }

}