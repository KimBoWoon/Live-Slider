package com.bowoon.android.live_slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.adapter.NewsItemAdapter
import com.bowoon.android.live_slider.adapter.ViewPagerAdapter
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest
import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.model.OGTag
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var newsItemAdapter: NewsItemAdapter
    private lateinit var mainNewsAdapter: ViewPagerAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val newsItems: ArrayList<Item> = ArrayList<Item>()
    private val mainNewsItems: ArrayList<Item> = ArrayList<Item>()
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        request()

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        newsItemAdapter = NewsItemAdapter(Glide.with(this))
        binding.mainNewsItems.adapter = newsItemAdapter
        binding.mainNewsItems.layoutManager = layoutManager
        mainNewsAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.mainViewPager.adapter = mainNewsAdapter

        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("1"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("2"))
        binding.mainTabLayout.addTab(binding.mainTabLayout.newTab().setText("3"))
    }

    private fun request() {
        HttpRequest.getMainNews(object : HttpCallback {
            override fun onSuccess(o: Any) {
                if (o is Channel) {
                    for (i in o.item) {
                        mainNewsItems.add(i)
                        i.ogTag = OGTag()

                        HttpRequest.getOGTag(i.link,  object : HttpCallback {
                            override fun onSuccess(o: Any) {
                                if (o is OGTag) {
                                    i.ogTag = o
                                }

                                mainNewsAdapter.setItems(mainNewsItems)
                                binding.executePendingBindings()
                            }

                            override fun onFail(o: Any) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }
                        })
                    }

                    mainNewsAdapter.setItems(mainNewsItems)
                    binding.executePendingBindings()
                }
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })

        HttpRequest.getNews(object : HttpCallback {
            override fun onSuccess(o: Any) {
                if (o is Channel) {
                    for (i in o.item) {
                        newsItems.add(i)
                        i.ogTag = OGTag()

                        HttpRequest.getOGTag(i.link,  object : HttpCallback {
                            override fun onSuccess(o: Any) {
                                if (o is OGTag) {
                                    i.ogTag = o
                                }

                                newsItemAdapter.setItems(newsItems)
                                binding.executePendingBindings()
                            }

                            override fun onFail(o: Any) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }
                        })
                    }

                    newsItemAdapter.setItems(newsItems)
                    binding.executePendingBindings()
                }
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }
}
