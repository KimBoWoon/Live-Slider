package com.bowoon.android.live_slider

import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bowoon.android.live_slider.adapter.AdapterOfMajorNews
import com.bowoon.android.live_slider.adapter.AdapterOfNewsKind
import com.bowoon.android.live_slider.animation.ViewPagerAnimation
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.http.AsyncTaskListener
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.type.NewsType
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterOfNewsKind: AdapterOfNewsKind
    private lateinit var adapterOfMajorNews: AdapterOfMajorNews
    private lateinit var layoutManager: LinearLayoutManager
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initView()
        request()
    }

    private fun initView() {
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        adapterOfNewsKind = AdapterOfNewsKind(supportFragmentManager, lifecycle)
        binding.mainKindOfNews.adapter = adapterOfNewsKind
        adapterOfMajorNews = AdapterOfMajorNews(supportFragmentManager, lifecycle)
        binding.mainMajorNews.adapter = adapterOfMajorNews
        binding.mainMajorNews.setPageTransformer(ViewPagerAnimation())

        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("전체"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("경제"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("사회"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("정치"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("지구촌"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("문화"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("IT"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("중앙데일리"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("스포츠"))
        binding.mainKindOfNewsTab.addTab(binding.mainKindOfNewsTab.newTab().setText("연예"))

        binding.mainKindOfNews.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.mainKindOfNewsTab.selectTab(binding.mainKindOfNewsTab.getTabAt(position))
            }
        })
        binding.mainKindOfNewsTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.mainKindOfNews.currentItem = tab!!.position
            }
        })
    }

    private fun success(items: ArrayList<Item>, type: NewsType) {
        when (type) {
            NewsType.MAIN -> {
                runOnUiThread(object : Runnable {
                    override fun run() {
                        adapterOfMajorNews.setItems(items)
                    }
                })
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getAllNews")
                    }

                    override fun onEventCompleted() {
                        runOnUiThread(object : Runnable {
                            override fun run() {
                                adapterOfMajorNews.setItems(items)
                            }
                        })
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, items)
            }

            else -> {
                runOnUiThread(object : Runnable {
                    override fun run() {
                        adapterOfNewsKind.setItems(items)
                    }
                })
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getAllNews")
                    }

                    override fun onEventCompleted() {
                        runOnUiThread(object : Runnable {
                            override fun run() {
                                adapterOfNewsKind.setItems(items)
                                binding.progressLayout.visibility = View.GONE
                            }
                        })
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                        binding.progressLayout.visibility = View.GONE
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, items)
            }
        }
    }

    private fun request() {
        HttpRequest.getAllNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.allNews, NewsType.ALL)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getMainNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.mainNews, NewsType.MAIN)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getMoneyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.moneyNews, NewsType.MONEY)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getLifeNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.lifeNews, NewsType.LIFE)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getPoliticsNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.politicsNews, NewsType.POLITICS)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getWorldNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.worldNews, NewsType.WORLD)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getCultureNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.cultureNews, NewsType.CULTURE)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getItNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.itNews, NewsType.IT)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getDailyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.dailyNews, NewsType.DAILY)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getSportNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.sportNews, NewsType.SPORT)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getStarNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                success(Data.starNews, NewsType.STAR)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}