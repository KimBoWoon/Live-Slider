package com.bowoon.android.live_slider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bowoon.android.live_slider.adapter.NewsItemAdapter
import com.bowoon.android.live_slider.adapter.MainNewsAdapter
import com.bowoon.android.live_slider.adapter.NewsAdapter
import com.bowoon.android.live_slider.animation.ViewPagerAnimation
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.http.AsyncTaskListener
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsItemAdapter: NewsAdapter
    private lateinit var mainNewsAdapter: MainNewsAdapter
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
        newsItemAdapter = NewsAdapter(supportFragmentManager, lifecycle)
        binding.mainNewsItems.adapter = newsItemAdapter
//        binding.mainNewsItems.layoutManager = layoutManager
        mainNewsAdapter = MainNewsAdapter(supportFragmentManager, lifecycle)
        binding.mainViewPager.adapter = mainNewsAdapter
        binding.mainViewPager.setPageTransformer(ViewPagerAnimation())

        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("전체"))
//        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("주요"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("경제"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("사회"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("정치"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("지구촌"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("문화"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("IT"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("중앙데일리"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("스포츠"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("연예"))

        binding.mainNewsItems.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.mainTabLayout.selectTab(binding.mainTabLayout.getTabAt(position))
            }
        })
        binding.mainTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.mainNewsItems.currentItem = tab!!.position
            }
        })
    }

    private fun request() {
        HttpRequest.getAllNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.allNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.allNews))
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
                mainNewsAdapter.setItems(ArrayList<Item>(Data.mainNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        mainNewsAdapter.setItems(ArrayList<Item>(Data.mainNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.moneyNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.moneyNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.lifeNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.lifeNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.politicsNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.politicsNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.worldNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.worldNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.cultureNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.cultureNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.itNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.itNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.dailyNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.dailyNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.sportNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.sportNews))
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
                newsItemAdapter.setItems(ArrayList<Item>(Data.starNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.starNews))
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