package com.bowoon.android.live_slider.http

import android.os.Build
import androidx.annotation.RequiresApi
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.OGTag
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import fr.arnaudguyon.xmltojsonlib.XmlToJson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object HttpRequest {
    private val TAG = "HttpRequest"
    private val BASE_URL = "https://rss.joins.com/"
    private val client: Retrofit
    private val service: HttpService
    private val gson: Gson
    private val parser: JsonParser

    init {
        client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(createOkHttpClient())
            .build()

        service = client.create(HttpService::class.java)
        gson = Gson()
        parser = JsonParser()
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.NONE
            })
            retryOnConnectionFailure(true)
        }.build()
    }

    private fun makeXmlToJson(body: String): JsonElement {
        val xmlToJson = XmlToJson.Builder(
            body
                .replace(System.getProperty("line.separator")!!, "")
                .replace("\t", "")
        ).build()

        return parser.parse(xmlToJson.toString())
    }

    fun getNews(callback: HttpCallback) {
        val call: Call<String> = service.getNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            @RequiresApi(Build.VERSION_CODES.KITKAT)
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val jsonElement = makeXmlToJson(response.body()!!).asJsonObject.get("rss").asJsonObject.get("channel").toString()
                val channel = gson.fromJson(jsonElement, Channel::class.java)
                Log.i(TAG, channel.toString())
                callback.onSuccess(channel)
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
                val jsonElement = makeXmlToJson(response.body()!!).asJsonObject.get("rss").asJsonObject.get("channel").toString()
                val channel = gson.fromJson(jsonElement, Channel::class.java)

//                for (i in 0 until channel.item.size) {
//                    channel.item[i].ogTag = OGTag()
//                    getOGTag(channel.item[i].link, object : HttpCallback {
//                        override fun onSuccess(o: Any) {
//                            if (o is OGTag) {
//                                channel.item[i].ogTag = o
//                            }
//                        }
//
//                        override fun onFail(o: Any) {
//                            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                        }
//                    })
//                }

                Log.i(TAG, channel.toString())
                callback.onSuccess(channel)
            }
        })
    }

    fun getOGTag(url: String, callback: HttpCallback) {
        val call: Call<String> = service.getOGTag(url)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                val ogTag = JsonObject()
                val jsonElement = makeXmlToJson(response.body()!!).asJsonObject.get("html").asJsonObject.get("head").asJsonObject
                val items = jsonElement.entrySet().iterator()
                for ((k, v) in items) {
                    if (k == "meta") {
                        for (valueItem in 0 until v.asJsonArray.size()) {
                            val item = v.asJsonArray.get(valueItem).asJsonObject.get("property")?.asString
                            if (item != null && (item.contains("(og)".toRegex()) || item.contains("(article)".toRegex()))) {
//                                Log.i(TAG, v.asJsonArray.get(valueItem).asJsonObject.get("content").asString)
                                ogTag.add("$item", v.asJsonArray.get(valueItem).asJsonObject.get("content"))
                            }
                        }
                    }
                }
                callback.onSuccess(gson.fromJson(ogTag, OGTag::class.java))
            }
        })
    }
}