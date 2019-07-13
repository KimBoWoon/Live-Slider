package com.bowoon.android.live_slider.http

import android.os.AsyncTask
import android.os.Build
import androidx.annotation.RequiresApi
import com.bowoon.android.live_slider.Data
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.model.OGTag
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jsoup.Jsoup
import org.jsoup.parser.Parser
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
    private lateinit var call: Call<String>

    init {
        client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
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
        }.build()
    }

    fun xmlParser(body: String, items: ArrayList<Item>) {
        val doc = Jsoup.parse(body, "", Parser.xmlParser())
        val elem = doc.select("item")
        val channel = Channel()
        var idx = 0
        channel.title = doc.select("title")[0].text()
        channel.link = doc.select("link")[0].text()
        channel.language = doc.select("language")[0].text()
        channel.copyright = doc.select("copyright")[0].text()
        channel.pubDate = doc.select("pubDate")[0].text()
        channel.lastBuildDate = doc.select("lastBuildDate")[0].text()
        for (item in elem) {
            items.add(Item(
                elem.select("title")[idx].text(),
                elem.select("link")[idx].text(),
                elem.select("description")[idx].text(),
                elem.select("author")[idx].text(),
                elem.select("pubDate")[idx].text(),
                OGTag())
            )
            idx++
        }
    }

    fun getAllNews(callback: HttpCallback) {
        val call: Call<String> = service.getAllNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.allNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getMainNews(callback: HttpCallback) {
        val call: Call<String> = service.getMainNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.mainNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getMoneyNews(callback: HttpCallback) {
        call = service.getMoneyNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.moneyNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getLifeNews(callback: HttpCallback) {
        call = service.getLifeNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.lifeNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getPoliticsNews(callback: HttpCallback) {
        call = service.getPoliticsNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.politicsNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getWorldNews(callback: HttpCallback) {
        call = service.getWorldNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.worldNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getCultureNews(callback: HttpCallback) {
        call = service.getCultureNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.cultureNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getItNews(callback: HttpCallback) {
        call = service.getITNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.itNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getDailyNews(callback: HttpCallback) {
        call = service.getDailyNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.dailyNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getSportNews(callback: HttpCallback) {
        call = service.getSportsNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.sportNews)
                callback.onSuccess(null)
            }
        })
    }

    fun getStarNews(callback: HttpCallback) {
        call = service.getStarNews()
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.i(TAG, t.message!!)
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                xmlParser(response.body()!!, Data.starNews)
                callback.onSuccess(null)
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

    class OGTagAsyncTask(private val listener: AsyncTaskListener) : AsyncTask<List<Item>, Unit, Unit>() {
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

        override fun doInBackground(vararg params: List<Item>?) {
            for (i in 0 until params[0]?.size!!) {
                val doc = Jsoup.connect(params[0]?.get(i)?.link!!).get()
                params[0]?.get(i)?.ogTag?.type = doc.select("meta[property=og:type]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.url = doc.select("meta[property=og:url]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.title = doc.select("meta[property=og:title]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.description = doc.select("meta[property=og:description]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.image = doc.select("meta[property=og:image]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.siteName = doc.select("meta[property=og:site_name]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.locale = doc.select("meta[property=og:locale]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.author = doc.select("meta[property=article:author]").first()?.attr("content")
                params[0]?.get(i)?.ogTag?.publisher = doc.select("meta[property=article:publisher]").first()?.attr("content")
            }
        }
    }
}