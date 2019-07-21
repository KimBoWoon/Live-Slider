package com.bowoon.android.live_slider.data

import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.type.NewsType

object Data {
    val allNews: ArrayList<Item> = ArrayList<Item>()
    val mainNews: ArrayList<Item> = ArrayList<Item>()
    val moneyNews: ArrayList<Item> = ArrayList<Item>()
    val lifeNews: ArrayList<Item> = ArrayList<Item>()
    val politicsNews: ArrayList<Item> = ArrayList<Item>()
    val worldNews: ArrayList<Item> = ArrayList<Item>()
    val cultureNews: ArrayList<Item> = ArrayList<Item>()
    val itNews: ArrayList<Item> = ArrayList<Item>()
    val dailyNews: ArrayList<Item> = ArrayList<Item>()
    val sportNews: ArrayList<Item> = ArrayList<Item>()
    val starNews: ArrayList<Item> = ArrayList<Item>()

    val BASE_URL = "https://rss.joins.com"
    val url = HashMap<NewsType, String>()

    init {
        url[NewsType.ALL] = "/joins_news_list.xml"
        url[NewsType.MAIN] = "/joins_homenews_list.xml"
        url[NewsType.MONEY] = "/joins_money_list.xml"
        url[NewsType.LIFE] = "/joins_life_list.xml"
        url[NewsType.POLITICS] = "/joins_politics_list.xml"
        url[NewsType.WORLD] = "/joins_world_list.xml"
        url[NewsType.CULTURE] = "/joins_culture_list.xml"
        url[NewsType.IT] = "/joins_it_list.xml"
        url[NewsType.DAILY] = "/news/joins_joongangdaily_news.xml"
        url[NewsType.SPORT] = "/joins_sports_list.xml"
        url[NewsType.STAR] = "/joins_star_list.xml"
    }
}