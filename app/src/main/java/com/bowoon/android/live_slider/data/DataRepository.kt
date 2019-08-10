package com.bowoon.android.live_slider.data

import androidx.lifecycle.MediatorLiveData
import com.bowoon.android.live_slider.data.model.Item
import com.bowoon.android.live_slider.data.model.OGTag
import com.bowoon.android.live_slider.data.model.Rss
import com.bowoon.android.live_slider.data.type.NewsType
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest

object DataRepository {
    private val observableAllNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableMainNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableMoneyNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableLifeNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observablePoliticsNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableWorldNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableCultureNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableItNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableDailyNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableSportNews: MediatorLiveData<Rss> = MediatorLiveData()
    private val observableStarNews: MediatorLiveData<Rss> = MediatorLiveData()

    val allNews: MediatorLiveData<Rss> get() = observableAllNews
    val mainNews: MediatorLiveData<Rss> get() = observableMainNews
    val moneyNews: MediatorLiveData<Rss> get() = observableMoneyNews
    val lifeNews: MediatorLiveData<Rss> get() = observableLifeNews
    val politicsNews: MediatorLiveData<Rss> get() = observablePoliticsNews
    val worldNews: MediatorLiveData<Rss> get() = observableWorldNews
    val cultureNews: MediatorLiveData<Rss> get() = observableCultureNews
    val itNews: MediatorLiveData<Rss> get() = observableItNews
    val dailyNews: MediatorLiveData<Rss> get() = observableDailyNews
    val sportNews: MediatorLiveData<Rss> get() = observableSportNews
    val starNews: MediatorLiveData<Rss> get() = observableStarNews

    private var allNewsCheck = false
    private var mainNewsCheck = false
    private var moneyNewsCheck = false
    private var lifeNewsCheck = false
    private var politicsNewsCheck = false
    private var worldNewsCheck = false
    private var cultureNewsCheck = false
    private var itNewsCheck = false
    private var dailyNewsCheck = false
    private var sportNewsCheck = false
    private var starNewsCheck = false

    private fun requestCheck(type: NewsType): Boolean {
        when (type) {
            NewsType.ALL -> {
                if (allNewsCheck == false) {
                    allNewsCheck = true
                    return true
                }
            }
            NewsType.MAIN -> {
                if (mainNewsCheck == false) {
                    mainNewsCheck = true
                    return true
                }
            }
            NewsType.MONEY -> {
                if (moneyNewsCheck == false) {
                    moneyNewsCheck = true
                    return true
                }
            }
            NewsType.LIFE -> {
                if (lifeNewsCheck == false) {
                    lifeNewsCheck = true
                    return true
                }
            }
            NewsType.POLITICS -> {
                if (politicsNewsCheck == false) {
                    politicsNewsCheck = true
                    return true
                }
            }
            NewsType.WORLD -> {
                if (worldNewsCheck == false) {
                    worldNewsCheck = true
                    return true
                }
            }
            NewsType.CULTURE -> {
                if (cultureNewsCheck == false) {
                    cultureNewsCheck = true
                    return true
                }
            }
            NewsType.IT -> {
                if (itNewsCheck == false) {
                    itNewsCheck = true
                    return true
                }
            }
            NewsType.DAILY -> {
                if (dailyNewsCheck == false) {
                    dailyNewsCheck = true
                    return true
                }
            }
            NewsType.SPORT -> {
                if (sportNewsCheck == false) {
                    sportNewsCheck = true
                    return true
                }
            }
            NewsType.STAR -> {
                if (starNewsCheck == true) {
                    starNewsCheck = true
                    return true
                }
            }
            NewsType.SEARCH -> {
                return false
            }
        }

        return false
    }

    fun typeRequest(type: NewsType) {
        if (requestCheck(type)) {
            when (type) {
                NewsType.ALL -> {
                    HttpRequest.getAllNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                allNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.MAIN -> {
                    HttpRequest.getMainNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                mainNews.value = o
                                for (item in mainNews.value!!.channel.item) {
                                    HttpRequest.getOGTag(item.link, object : HttpCallback {
                                        override fun onSuccess(o: Any?) {
                                            if (o is OGTag) {
                                                item.ogTag = o
                                            }
                                        }

                                        override fun onFail(o: Any) {

                                        }
                                    })
                                }
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.MONEY -> {
                    HttpRequest.getMoneyNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                moneyNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.LIFE -> {
                    HttpRequest.getLifeNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                lifeNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.POLITICS -> {
                    HttpRequest.getPoliticsNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                politicsNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.WORLD -> {
                    HttpRequest.getWorldNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                worldNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.CULTURE -> {
                    HttpRequest.getCultureNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                cultureNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.IT -> {
                    HttpRequest.getItNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                itNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.DAILY -> {
                    HttpRequest.getDailyNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                dailyNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.SPORT -> {
                    HttpRequest.getSportNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                sportNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.STAR -> {
                    HttpRequest.getStarNews(object : HttpCallback {
                        override fun onSuccess(o: Any?) {
                            if (o is Rss) {
                                starNews.value = o
                            }
                        }

                        override fun onFail(o: Any) {

                        }
                    })
                }
                NewsType.SEARCH -> {
                }
            }
        }
    }

    fun ogTagRequest(items: List<Item>, callback: HttpCallback) {
        for (item in items) {
            if (item.ogTag.url == "") {
                HttpRequest.getOGTag(item.link, object : HttpCallback {
                    override fun onSuccess(o: Any?) {
                        if (o is OGTag) {
                            item.ogTag = o
                            callback.onSuccess(o)
                        }
                    }

                    override fun onFail(o: Any) {

                    }
                })
            }
        }
    }
}