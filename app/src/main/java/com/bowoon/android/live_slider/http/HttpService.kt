package com.bowoon.android.live_slider.http

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface HttpService {
    @GET("/joins_news_list.xml")
    fun getAllNews(): Call<String>

    @GET("/joins_homenews_list.xml")
    fun getMainNews(): Call<String>

    @GET("/joins_money_list.xml")
    fun getMoneyNews(): Call<String>

    @GET("/joins_life_list.xml")
    fun getLifeNews(): Call<String>

    @GET("/joins_politics_list.xml")
    fun getPoliticsNews(): Call<String>

    @GET("/joins_world_list.xml")
    fun getWorldNews(): Call<String>

    @GET("/joins_culture_list.xml")
    fun getCultureNews(): Call<String>

    @GET("/joins_it_list.xml")
    fun getITNews(): Call<String>

    @GET("/news/joins_joongangdaily_news.xml")
    fun getDailyNews(): Call<String>

    @GET("/joins_sports_list.xml")
    fun getSportsNews(): Call<String>

    @GET("/joins_star_list.xml")
    fun getStarNews(): Call<String>

//    @GET
//    fun getOGTag(@Url url: String): Call<String>
}