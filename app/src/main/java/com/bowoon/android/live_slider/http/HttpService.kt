package com.bowoon.android.live_slider.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface HttpService {
    @GET("/joins_news_list.xml")
    fun getNews(): Call<String>

    @GET("/joins_homenews_list.xml")
    fun getMainNew(): Call<String>

    @GET
    fun getOGTag(@Url url: String): Call<String>
}