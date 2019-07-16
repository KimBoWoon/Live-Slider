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

    private fun request() {
        HttpRequest.RSSParserAsyncTask(object : AsyncTaskListener {
            override fun startEvent() {
                Log.i(TAG, "Started")
            }

            override fun onEventCompleted() {
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "Started")
                    }

                    override fun onEventCompleted() {
                        adapterOfNewsKind.setItems(Data.allNews)
                        adapterOfNewsKind.setItems(Data.moneyNews)
                        adapterOfNewsKind.setItems(Data.lifeNews)
                        adapterOfNewsKind.setItems(Data.politicsNews)
                        adapterOfNewsKind.setItems(Data.worldNews)
                        adapterOfNewsKind.setItems(Data.cultureNews)
                        adapterOfNewsKind.setItems(Data.itNews)
                        adapterOfNewsKind.setItems(Data.dailyNews)
                        adapterOfNewsKind.setItems(Data.sportNews)
                        adapterOfNewsKind.setItems(Data.starNews)
                        adapterOfMajorNews.setItems(Data.mainNews)
                        Log.i(TAG, "Completed")
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "Failed")
                    }
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
            }

            override fun onEventFailed() {

            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
    }
}