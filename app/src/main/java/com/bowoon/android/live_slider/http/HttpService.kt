package com.bowoon.android.live_slider.http

import retrofit2.Call
import retrofit2.http.GET

interface HttpService {
    @GET("/joins_news_list.xml")
    fun getRSS(): Call<String>
}