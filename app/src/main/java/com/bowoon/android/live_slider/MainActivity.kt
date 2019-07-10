package com.bowoon.android.live_slider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.adapter.NewsItemAdapter
import com.bowoon.android.live_slider.adapter.ViewPagerAdapter
import com.bowoon.android.live_slider.animation.ViewPagerAnimation
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.http.AsyncTaskListener
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsItemAdapter: NewsItemAdapter
    private lateinit var mainNewsAdapter: ViewPagerAdapter
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
        newsItemAdapter = NewsItemAdapter(Glide.with(this))
        binding.mainNewsItems.adapter = newsItemAdapter
        binding.mainNewsItems.layoutManager = layoutManager
        mainNewsAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.mainViewPager.adapter = mainNewsAdapter
        binding.mainViewPager.setPageTransformer(ViewPagerAnimation())

        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("전체"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("주요"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("경제"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("사회"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("정치"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("지구촌"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("문화"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("IT"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("중앙데일리"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("스포츠"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("연예"))
    }

    private fun request() {
        HttpRequest.getAllNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.allNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.allNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.allNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getMainNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                mainNewsAdapter.setItems(ArrayList<Item>(Data.mainNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        mainNewsAdapter.setItems(ArrayList<Item>(Data.mainNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.mainNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getMoneyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.moneyNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.moneyNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.moneyNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getLifeNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.lifeNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.lifeNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.lifeNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getPoliticsNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.politicsNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.politicsNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.politicsNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getWorldNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.worldNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.worldNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.worldNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getCultureNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.cultureNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.cultureNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.cultureNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getItNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.itNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.itNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.itNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getDailyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.dailyNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.dailyNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.dailyNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getSportNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.sportNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.sportNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.sportNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getStarNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.starNews.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.starNews.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.starNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}