package com.bowoon.android.live_slider.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bowoon.android.live_slider.data.Data
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.adapter.AdapterOfMajorNews
import com.bowoon.android.live_slider.adapter.AdapterOfNewsKind
import com.bowoon.android.live_slider.animation.ViewPagerAnimation
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.http.HttpCallback
import com.bowoon.android.live_slider.http.HttpRequest
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.model.Rss
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

        layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        adapterOfMajorNews = AdapterOfMajorNews(supportFragmentManager, lifecycle)
        binding.mainMajorNews.adapter = adapterOfMajorNews
        binding.mainMajorNews.setPageTransformer(ViewPagerAnimation())
        adapterOfNewsKind = AdapterOfNewsKind(binding.mainKindOfNewsTab.tabCount, supportFragmentManager, lifecycle)
        binding.mainKindOfNews.adapter = adapterOfNewsKind

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val searchView: SearchView = (menu!!.findItem(R.id.action_search).actionView) as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.queryHint = "검색어를 입력하세요."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.i(TAG, query!!)
                val result = ArrayList<Item>()

                for (item in Data.allNews) {
                    if (item.title.contains(query)) {
                        result.add(item)
                    }
                }

                val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
                intent.putExtra("result", result)
                startActivity(intent)

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.action_search) {
            Log.i(TAG, "action_search")
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun request() {
        HttpRequest.getAllNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.allNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getMainNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.mainNews.add(item)
                    }
                    adapterOfMajorNews.setItems(Data.mainNews)
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getMoneyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.moneyNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getLifeNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.lifeNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getPoliticsNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.politicsNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getWorldNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.worldNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getCultureNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.cultureNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getItNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.itNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getDailyNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.dailyNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getSportNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.sportNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })

        HttpRequest.getStarNews(object : HttpCallback {
            override fun onSuccess(o: Any?) {
                if (o is Rss) {
                    for (item in o.channel.item) {
                        Data.starNews.add(item)
                    }
                }
            }

            override fun onFail(o: Any) {

            }
        })
    }
}