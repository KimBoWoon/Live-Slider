package com.bowoon.android.live_slider.activity

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.adapter.AdapterOfMajorNews
import com.bowoon.android.live_slider.adapter.AdapterOfNewsKind
import com.bowoon.android.live_slider.animation.ViewPagerAnimation
import com.bowoon.android.live_slider.data.Constant
import com.bowoon.android.live_slider.data.DataRepository
import com.bowoon.android.live_slider.data.SharedStorage
import com.bowoon.android.live_slider.data.model.Rss
import com.bowoon.android.live_slider.data.type.NewsType
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.viewmodel.DataViewModel
import com.google.android.material.tabs.TabLayout
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions
import java.util.*

class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterOfNewsKind: AdapterOfNewsKind
    private lateinit var adapterOfMajorNews: AdapterOfMajorNews
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var majorNewsViewModel: DataViewModel
    private val requestCode = 1000
    private val TAG = "MainActivity"
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requiresPermission()

//        if (SharedStorage.getBoolean("isFirst")) {
//            startActivityForResult(Intent(this, SettingActivity::class.java), requestCode)
//        }

        initViewModel()
        initView()
        DataRepository.request()
        settingTimer()
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
        binding.mainMajorNews.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
//                when (state) {
//                    ViewPager2.SCROLL_STATE_IDLE -> {
//
//                    }
//                    ViewPager2.SCROLL_STATE_DRAGGING -> {
//
//                    }
//                    ViewPager2.SCROLL_STATE_SETTLING -> {
//                        if (adapterOfMajorNews.itemCount != currentPage) {
//                            currentPage++
//                        }
//                    }
//                }
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentPage = position
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

                searchData(query)

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

    private fun searchData(query: String) {
        majorNewsViewModel.getSearchData(query).observe(this, object : Observer<Rss> {
            override fun onChanged(t: Rss?) {
                t?.let {
                    val intent = Intent(this@MainActivity, SearchResultActivity::class.java)
                    intent.putExtra("title", query)
                    intent.putExtra("result", it.channel.item)
                    startActivity(intent)
                }
            }
        })
    }

    private fun settingTimer() {
        val slideTimer = Timer()
        slideTimer.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread(object : Runnable {
                    override fun run() {
                        if (adapterOfMajorNews.itemCount == currentPage) {
                            currentPage = 0
                        }
                        binding.mainMajorNews.currentItem = currentPage++
                    }
                })
            }
        }, 3000, 3000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            when (requestCode) {
                this.requestCode -> {
                    SharedStorage.setBoolean("isFirst", false)
                }
            }
        }
    }

    @AfterPermissionGranted(Constant.INTERNET)
    private fun requiresPermission() {
        val perms = arrayOf(Manifest.permission.INTERNET)
        if (EasyPermissions.hasPermissions(this, *perms)) {
            // Already have permission, do the thing
            // ...
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.need_permissions),
                Constant.INTERNET,
                *perms
            )
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.i("Permission", "Denied")
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.i("Permission", "Granted")
    }

    private fun initViewModel() {
        majorNewsViewModel = ViewModelProviders.of(this).get(DataViewModel::class.java)
        majorNewsViewModel.getRSS(NewsType.MAIN).observe(this, object : Observer<Rss> {
            override fun onChanged(t: Rss?) {
                t?.let {
                    adapterOfMajorNews.setItems(it.channel.item)
                }
            }
        })
    }
}