package com.bowoon.android.live_slider.http

import android.os.AsyncTask
import com.bowoon.android.live_slider.Data
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.*
import com.bowoon.android.live_slider.model.adapter.*
import com.bowoon.android.live_slider.type.NewsType
import com.tickaroo.tikxml.TikXml
import com.tickaroo.tikxml.retrofit.TikXmlConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.util.*
import java.util.concurrent.TimeUnit

object HttpRequest {
    private const val TAG = "HttpRequest"
    private const val BASE_URL = "https://rss.joins.com/"
    private val client: Retrofit
    private val service: HttpService
    private lateinit var call: Call<Rss>
    private val tikXml: TikXml

    init {
        tikXml = TikXml.Builder()
            .addTypeConverter(Date::class.java, MyDateConverter())
            .addTypeAdapter(Rss::class.java, RssTypeAdapter())
            .addTypeAdapter(Channel::class.java, ChannelTypeAdapter())
            .addTypeAdapter(Image::class.java, ImageTypeAdapter())
            .addTypeAdapter(Item::class.java, ItemTypeAdapter())
            .build()

        client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(TikXmlConverterFactory.create(tikXml))
            .client(createOkHttpClient())
            .build()

        service = client.create(HttpService::class.java)
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
        call = service.getAllNews()
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
        call = service.getMainNews()
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
        call = service.getMoneyNews()
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
        call = service.getLifeNews()
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
        call = service.getPoliticsNews()
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
        call = service.getWorldNews()
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
        call = service.getCultureNews()
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
        call = service.getITNews()
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
        call = service.getDailyNews()
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
        call = service.getSportsNews()
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
        call = service.getStarNews()
        call.enqueue(object : Callback<Rss> {
            override fun onFailure(call: Call<Rss>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<Rss>, response: Response<Rss>) {
                callback.onSuccess(response.body())
            }
        })
    }

//    fun getOGTag(items: ArrayList<Item>, callback: HttpCallback) {
//        for (i in items) {
//            call = service.getOGTag(i.link)
//            call.enqueue(object : Callback<String> {
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    Log.i(TAG, t.message!!)
//                }
//
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    val doc = Jsoup.parse(response.body()!!)
//                    i.ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
//                    i.ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
//                    i.ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
//                    i.ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
//                    i.ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
//                    i.ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
//                    i.ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
//                    i.ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
//                    i.ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
//                }
//            })
//        }
//        callback.onSuccess(null)
//    }

    class RSSParserAsyncTask(private val listener: AsyncTaskListener) : AsyncTask<Unit, Unit, Unit>() {
        override fun onProgressUpdate(vararg values: Unit?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            listener.onEventCompleted()
        }

        override fun onCancelled(result: Unit?) {
            super.onCancelled(result)
            listener.onEventFailed()
        }

        override fun onCancelled() {
            super.onCancelled()
            listener.onEventFailed()
        }

        override fun onPreExecute() {
            super.onPreExecute()
            listener.startEvent()
        }

        override fun doInBackground(vararg params: Unit?) {
            for (i in Data.url.keys) {
                val doc = Jsoup.connect(Data.BASE_URL + Data.url[i]).get()
                val elem = doc.select("item")
//                val channel = Channel()
//                channel.title = doc.select("title")[0].text()
//                channel.link = doc.select("link")[0].text()
//                channel.language = doc.select("language")[0].text()
//                channel.copyright = doc.select("copyright")[0].text()
//                channel.pubDate = doc.select("pubDate")[0].text()
//                channel.lastBuildDate = doc.select("lastBuildDate")[0].text()
                for (item in elem) {
                    when (i) {
                        NewsType.ALL -> {
                            Data.allNews.add(
                                Item(
                                    elem.select("title")[Data.allNews.size].text(),
                                    elem.select("link")[Data.allNews.size].text(),
                                    elem.select("description")[Data.allNews.size].text(),
                                    elem.select("author")[Data.allNews.size].text(),
                                    elem.select("pubDate")[Data.allNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.MAIN -> {
                            Data.mainNews.add(
                                Item(
                                    elem.select("title")[Data.mainNews.size].text(),
                                    elem.select("link")[Data.mainNews.size].text(),
                                    elem.select("description")[Data.mainNews.size].text(),
                                    elem.select("author")[Data.mainNews.size].text(),
                                    elem.select("pubDate")[Data.mainNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.MONEY -> {
                            Data.moneyNews.add(
                                Item(
                                    elem.select("title")[Data.moneyNews.size].text(),
                                    elem.select("link")[Data.moneyNews.size].text(),
                                    elem.select("description")[Data.moneyNews.size].text(),
                                    elem.select("author")[Data.moneyNews.size].text(),
                                    elem.select("pubDate")[Data.moneyNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.LIFE -> {
                            Data.lifeNews.add(
                                Item(
                                    elem.select("title")[Data.lifeNews.size].text(),
                                    elem.select("link")[Data.lifeNews.size].text(),
                                    elem.select("description")[Data.lifeNews.size].text(),
                                    elem.select("author")[Data.lifeNews.size].text(),
                                    elem.select("pubDate")[Data.lifeNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.POLITICS -> {
                            Data.politicsNews.add(
                                Item(
                                    elem.select("title")[Data.politicsNews.size].text(),
                                    elem.select("link")[Data.politicsNews.size].text(),
                                    elem.select("description")[Data.politicsNews.size].text(),
                                    elem.select("author")[Data.politicsNews.size].text(),
                                    elem.select("pubDate")[Data.politicsNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.WORLD -> {
                            Data.worldNews.add(
                                Item(
                                    elem.select("title")[Data.worldNews.size].text(),
                                    elem.select("link")[Data.worldNews.size].text(),
                                    elem.select("description")[Data.worldNews.size].text(),
                                    elem.select("author")[Data.worldNews.size].text(),
                                    elem.select("pubDate")[Data.worldNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.CULTURE -> {
                            Data.cultureNews.add(
                                Item(
                                    elem.select("title")[Data.cultureNews.size].text(),
                                    elem.select("link")[Data.cultureNews.size].text(),
                                    elem.select("description")[Data.cultureNews.size].text(),
                                    elem.select("author")[Data.cultureNews.size].text(),
                                    elem.select("pubDate")[Data.cultureNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.IT -> {
                            Data.itNews.add(
                                Item(
                                    elem.select("title")[Data.itNews.size].text(),
                                    elem.select("link")[Data.itNews.size].text(),
                                    elem.select("description")[Data.itNews.size].text(),
                                    elem.select("author")[Data.itNews.size].text(),
                                    elem.select("pubDate")[Data.itNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.DAILY -> {
                            Data.dailyNews.add(
                                Item(
                                    elem.select("title")[Data.dailyNews.size].text(),
                                    elem.select("link")[Data.dailyNews.size].text(),
                                    elem.select("description")[Data.dailyNews.size].text(),
                                    elem.select("author")[Data.dailyNews.size].text(),
                                    elem.select("pubDate")[Data.dailyNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.SPORT -> {
                            Data.sportNews.add(
                                Item(
                                    elem.select("title")[Data.sportNews.size].text(),
                                    elem.select("link")[Data.sportNews.size].text(),
                                    elem.select("description")[Data.sportNews.size].text(),
                                    elem.select("author")[Data.sportNews.size].text(),
                                    elem.select("pubDate")[Data.sportNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                        NewsType.STAR -> {
                            Data.starNews.add(
                                Item(
                                    elem.select("title")[Data.starNews.size].text(),
                                    elem.select("link")[Data.starNews.size].text(),
                                    elem.select("description")[Data.starNews.size].text(),
                                    elem.select("author")[Data.starNews.size].text(),
                                    elem.select("pubDate")[Data.starNews.size].text(),
                                    OGTag()
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    class OGTagAsyncTask(private val listener: AsyncTaskListener) : AsyncTask<Unit, Unit, Unit>() {
        override fun onProgressUpdate(vararg values: Unit?) {
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)
            listener.onEventCompleted()
        }

        override fun onCancelled(result: Unit?) {
            super.onCancelled(result)
            listener.onEventFailed()
        }

        override fun onCancelled() {
            super.onCancelled()
            listener.onEventFailed()
        }

        override fun onPreExecute() {
            super.onPreExecute()
            listener.startEvent()
        }

        override fun doInBackground(vararg params: Unit?) {
            var doc: Document
            var idx = 0
            for (i in Data.allNews) {
                doc = Jsoup.connect(i.link).get()
                Data.allNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.allNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.allNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.allNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.allNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.allNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.allNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.allNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.allNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.mainNews) {
                doc = Jsoup.connect(i.link).get()
                Data.mainNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.mainNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.mainNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.mainNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.mainNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.mainNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.mainNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.mainNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.mainNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.moneyNews) {
                doc = Jsoup.connect(i.link).get()
                Data.moneyNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.moneyNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.lifeNews) {
                doc = Jsoup.connect(i.link).get()
                Data.lifeNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.lifeNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.politicsNews) {
                doc = Jsoup.connect(i.link).get()
                Data.politicsNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.politicsNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.worldNews) {
                doc = Jsoup.connect(i.link).get()
                Data.worldNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.worldNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.worldNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.worldNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.worldNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.worldNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.worldNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.worldNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.worldNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.cultureNews) {
                doc = Jsoup.connect(i.link).get()
                Data.cultureNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.cultureNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.itNews) {
                doc = Jsoup.connect(i.link).get()
                Data.itNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.itNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.itNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.itNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.itNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.itNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.itNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.itNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.itNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.dailyNews) {
                doc = Jsoup.connect(i.link).get()
                Data.dailyNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.dailyNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.sportNews) {
                doc = Jsoup.connect(i.link).get()
                Data.sportNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.sportNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.sportNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.sportNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.sportNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.sportNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.sportNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.sportNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.sportNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
            for (i in Data.starNews) {
                doc = Jsoup.connect(i.link).get()
                Data.starNews[idx].ogTag.type = doc.select("meta[property=og:type]").first()?.attr("content")
                Data.starNews[idx].ogTag.url = doc.select("meta[property=og:url]").first()?.attr("content")
                Data.starNews[idx].ogTag.title = doc.select("meta[property=og:title]").first()?.attr("content")
                Data.starNews[idx].ogTag.description = doc.select("meta[property=og:description]").first()?.attr("content")
                Data.starNews[idx].ogTag.image = doc.select("meta[property=og:image]").first()?.attr("content")
                Data.starNews[idx].ogTag.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                Data.starNews[idx].ogTag.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                Data.starNews[idx].ogTag.author = doc.select("meta[property=article:author]").first()?.attr("content")
                Data.starNews[idx].ogTag.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
                idx++
            }
            idx = 0
        }
    }
}