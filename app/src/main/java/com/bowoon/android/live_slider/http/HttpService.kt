package com.bowoon.android.live_slider.http

import com.bowoon.android.live_slider.model.OGTag
import com.bowoon.android.live_slider.model.Rss
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface HttpService {
    @GET("/joins_news_list.xml")
    fun getAllNews(): Call<Rss>

    @GET("/joins_homenews_list.xml")
    fun getMainNews(): Call<Rss>

    @GET("/joins_money_list.xml")
    fun getMoneyNews(): Call<Rss>

    @GET("/joins_life_list.xml")
    fun getLifeNews(): Call<Rss>

    @GET("/joins_politics_list.xml")
    fun getPoliticsNews(): Call<Rss>

    @GET("/joins_world_list.xml")
    fun getWorldNews(): Call<Rss>

    @GET("/joins_culture_list.xml")
    fun getCultureNews(): Call<Rss>

    @GET("/joins_it_list.xml")
    fun getITNews(): Call<Rss>

    @GET("/news/joins_joongangdaily_news.xml")
    fun getDailyNews(): Call<Rss>

    @GET("/joins_sports_list.xml")
    fun getSportsNews(): Call<Rss>

    @GET("/joins_star_list.xml")
    fun getStarNews(): Call<Rss>

    @GET
    fun getOGTag(@Url url: String): Call<OGTag>
}