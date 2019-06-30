package com.bowoon.android.live_slider

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.adapter.NewsItemAdapter
import com.bowoon.android.live_slider.adapter.ViewPagerAdapter
import com.bowoon.android.live_slider.databinding.ActivityMainBinding
import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.Item

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: NewsItemAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val items:ArrayList<Item> = ArrayList<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.mainViewPager.adapter = ViewPagerAdapter(3, supportFragmentManager)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        adapter = NewsItemAdapter()
        binding.mainNewsItems.adapter = adapter
        binding.mainNewsItems.layoutManager = layoutManager

        val r = RetrofitClass()
        r.getRSS(object : HttpCallback {
            override fun onSuccess(o: Any) {
                if (o is Channel) {
                    for (i in o.item) {
                        items.add(i)
                    }

                    adapter.setItems(items)
                    binding.executePendingBindings()
                }
            }

            override fun onFail(o: Any) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
