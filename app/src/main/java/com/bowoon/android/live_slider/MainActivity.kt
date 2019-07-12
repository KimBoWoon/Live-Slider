package com.bowoon.android.live_slider

import android.os.AsyncTask
import android.os.Bundle
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

    private fun request() {
        HttpRequest.getAllNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.allNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getAllNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.allNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.allNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getMainNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfMajorNews.setItems(ArrayList<Item>(Data.mainNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getMainNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfMajorNews.setItems(ArrayList<Item>(Data.mainNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.mainNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getMoneyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.moneyNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getMoneyNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.moneyNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.moneyNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getLifeNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.lifeNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getLifeNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.lifeNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.lifeNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getPoliticsNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.politicsNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getPoliticsNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.politicsNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.politicsNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getWorldNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.worldNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getWorldNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.worldNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.worldNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getCultureNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.cultureNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getCultureNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.cultureNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.cultureNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getItNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.itNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getItNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.itNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.itNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getDailyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.dailyNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getDailyNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.dailyNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.dailyNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getSportNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.sportNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getSportNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.sportNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.sportNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getStarNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                adapterOfNewsKind.setItems(ArrayList<Item>(Data.starNews))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "getStarNews")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(ArrayList<Item>(Data.starNews))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Data.starNews)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}