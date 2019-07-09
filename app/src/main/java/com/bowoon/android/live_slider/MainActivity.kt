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

        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("1"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("2"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("3"))
    }

    private fun request() {
        HttpRequest.getMainNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                mainNewsAdapter.setItems(ArrayList<Item>(Data.mainNewsItems.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        mainNewsAdapter.setItems(ArrayList<Item>(Data.mainNewsItems.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.mainNewsItems)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                newsItemAdapter.setItems(ArrayList<Item>(Data.newsItems.subList(0, 5)))
                HttpRequest.OGTagAsyncTask(object : AsyncTaskListener {
                    override fun startEvent() {
                        Log.i(TAG, "loading...")
                    }

                    override fun onEventCompleted() {
                        newsItemAdapter.setItems(ArrayList<Item>(Data.newsItems.subList(0, 5)))
                    }

                    override fun onEventFailed() {
                        Log.i(TAG, "event failed")
                    }
                }).execute(Data.newsItems)
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}