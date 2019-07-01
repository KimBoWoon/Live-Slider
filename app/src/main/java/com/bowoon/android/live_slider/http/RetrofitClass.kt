package com.bowoon.android.live_slider.http

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


class RetrofitClass {
    private val TAG = "RetrofitClass"
    private val BASE_URL = "https://rss.joins.com/"

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            retryOnConnectionFailure(true)
        }.build()
    }

    fun getRSS(callback: HttpCallback) {
        val client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(createOkHttpClient())
            .build()
        val service = client.create(HttpService::class.java)

        val call: Call<String> = service.getRSS()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val xmlToJson = XmlToJson.Builder(response.body()!!).build()
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
}