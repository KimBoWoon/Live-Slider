package com.bowoon.android.live_slider.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.adapter.AdapterOfNews
import com.bowoon.android.live_slider.databinding.SearchViewBinding
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.module.GlideApp

class SearchResultActivity : AppCompatActivity() {
    private lateinit var binding: SearchViewBinding
    private lateinit var adapterOfNews: AdapterOfNews
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val result = intent.getSerializableExtra("result") as ArrayList<Item>

        supportActionBar!!.title = "${intent.getStringExtra("title")}의 검색결과"

        binding = DataBindingUtil.setContentView(this, R.layout.search_view)
        adapterOfNews = AdapterOfNews(GlideApp.with(this@SearchResultActivity))
        layoutManager = LinearLayoutManager(this@SearchResultActivity, RecyclerView.VERTICAL, false)
        adapterOfNews.setItems(result)
        binding.searchItems.layoutManager = layoutManager
        binding.searchItems.adapter = adapterOfNews
    }
}