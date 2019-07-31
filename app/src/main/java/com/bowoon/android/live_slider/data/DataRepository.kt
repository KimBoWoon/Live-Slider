package com.bowoon.android.live_slider.data

import androidx.lifecycle.MediatorLiveData
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest
import com.bowoon.android.live_slider.model.OGTag
import com.bowoon.android.live_slider.model.Rss

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

    fun request() {
        HttpRequest.getAllNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    allNews.value = o
                    for (item in allNews.value!!.channel.item) {
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

        HttpRequest.getMoneyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    moneyNews.value = o
                    for (item in moneyNews.value!!.channel.item) {
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

        HttpRequest.getLifeNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    lifeNews.value = o
                    for (item in lifeNews.value!!.channel.item) {
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

        HttpRequest.getPoliticsNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    politicsNews.value = o
                    for (item in politicsNews.value!!.channel.item) {
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

        HttpRequest.getWorldNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    worldNews.value = o
                    for (item in worldNews.value!!.channel.item) {
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

        HttpRequest.getCultureNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    cultureNews.value = o
                    for (item in cultureNews.value!!.channel.item) {
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

        HttpRequest.getItNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    itNews.value = o
                    for (item in itNews.value!!.channel.item) {
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

        HttpRequest.getDailyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    dailyNews.value = o
                    for (item in dailyNews.value!!.channel.item) {
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

        HttpRequest.getSportNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    sportNews.value = o
                    for (item in sportNews.value!!.channel.item) {
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

        HttpRequest.getStarNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    starNews.value = o
                    for (item in starNews.value!!.channel.item) {
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
}