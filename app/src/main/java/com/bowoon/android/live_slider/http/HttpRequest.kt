package com.bowoon.android.live_slider.http

import android.os.Build
import androidx.annotation.RequiresApi
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Channel
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import retrofit2.converter.scalars.ScalarsConverterFactory

object HttpRequest {
    private val TAG = "HttpRequest"
    private val BASE_URL = "https://rss.joins.com/"
    private val client: Retrofit
    private val service: HttpService

    init {
        client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(createOkHttpClient())
            .build()

        service = client.create(HttpService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            retryOnConnectionFailure(true)
        }.build()
    }

    fun getNews(callback: HttpCallback) {
        val call: Call<String> = service.getNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val responseString =
                    response.body()!!.replace(System.getProperty("line.separator")!!, "").replace("\t", "")
                val xmlToJson = XmlToJson.Builder(responseString).build()
                Log.i(TAG, xmlToJson.toString())
                val parser = JsonParser()
                val jsonString = parser.parse(xmlToJson.toString()).asJsonObject.get("rss").asJsonObject.get("channel")
                Log.i(TAG, jsonString.toString())
                val gson = Gson()
                val str = gson.fromJson(jsonString, Channel::class.java)
                Log.i(TAG, str.toString())
                callback.onSuccess(str)
            }
        })
    }

    fun getMainNews(callback: HttpCallback) {
        val call: Call<String> = service.getMainNew()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val responseString =
                    response.body()!!.replace(System.getProperty("line.separator")!!, "").replace("\t", "")
                val xmlToJson = XmlToJson.Builder(responseString).build()
                Log.i(TAG, xmlToJson.toString())
                val parser = JsonParser()
                val jsonString = parser.parse(xmlToJson.toString()).asJsonObject.get("rss").asJsonObject.get("channel")
                Log.i(TAG, jsonString.toString())
                val gson = Gson()
                val str = gson.fromJson(jsonString, Channel::class.java)
                Log.i(TAG, str.toString())
                callback.onSuccess(str)
            }
        })
    }

    private fun getOGTag(url: String) {
        val call: Call<String> = service.getMainNew()
    }
}