package com.bowoon.android.live_slider.http

import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.data.model.*
import com.bowoon.android.live_slider.data.model.adapter.*
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.droidsonroids.retrofit2.JspoonConverterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit

object HttpRequest {
    private const val TAG = "HttpRequest"
    private const val BASE_URL = "https://rss.joins.com/"
    private val xmlRetrofit: Retrofit
    private val xmlService: HttpService
    private val htmlRetrofit: Retrofit
    private val htmlService: HttpService
    private lateinit var call: Call<Rss>
    private val tikXml: TikXml

    init {
        tikXml = TikXml.Builder()
            .addTypeConverter(Date::class.java, DateConverter())
            .addTypeAdapter(Rss::class.java, RssTypeAdapter())
            .addTypeAdapter(Channel::class.java, ChannelTypeAdapter())
            .addTypeAdapter(Image::class.java, ImageTypeAdapter())
            .addTypeAdapter(Item::class.java, ItemTypeAdapter())
            .build()

        xmlRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(TikXmlConverterFactory.create(tikXml))
            .client(createOkHttpClient())
            .build()

        xmlService = xmlRetrofit.create(HttpService::class.java)

        htmlRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JspoonConverterFactory.create())
            .client(createOkHttpClient())
            .build()

        htmlService = htmlRetrofit.create(HttpService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.NONE
            })
            retryOnConnectionFailure(true)
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
        }.build()
    }

    fun getAllNews(callback: HttpCallback) {
        call = xmlService.getAllNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getMainNews(callback: HttpCallback) {
        call = xmlService.getMainNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getMoneyNews(callback: HttpCallback) {
        call = xmlService.getMoneyNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getLifeNews(callback: HttpCallback) {
        call = xmlService.getLifeNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getPoliticsNews(callback: HttpCallback) {
        call = xmlService.getPoliticsNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getWorldNews(callback: HttpCallback) {
        call = xmlService.getWorldNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getCultureNews(callback: HttpCallback) {
        call = xmlService.getCultureNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getItNews(callback: HttpCallback) {
        call = xmlService.getITNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getDailyNews(callback: HttpCallback) {
        call = xmlService.getDailyNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getSportNews(callback: HttpCallback) {
        call = xmlService.getSportsNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getStarNews(callback: HttpCallback) {
        call = xmlService.getStarNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

    fun getOGTag(link: String, callback: HttpCallback) {
        val call = htmlService.getOGTag(link)
        call.enqueue(object : Callback<OGTag> {
            override fun onFailure(call: Call<OGTag>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<OGTag>, response: Response<OGTag>) {
                callback.onSuccess(response.body())
            }
        })
    }
}