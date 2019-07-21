package com.bowoon.android.live_slider.activity

import android.app.Activity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.R
import com.bowoon.android.live_slider.adapter.AdapterOfNews
import com.bowoon.android.live_slider.databinding.NewsItemViewBinding
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.Glide

class SearchResultActivity : Activity() {
    private lateinit var binding: NewsItemViewBinding
    private lateinit var adapterOfNews: AdapterOfNews
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val result = intent.getSerializableExtra("result") as ArrayList<Item>

        binding = DataBindingUtil.setContentView(this, R.layout.news_item_view)
        adapterOfNews = AdapterOfNews(Glide.with(this@SearchResultActivity))
        layoutManager = LinearLayoutManager(this@SearchResultActivity, RecyclerView.VERTICAL, false)
        adapterOfNews.setItems(result)
        binding.fragmentNewsView.layoutManager = layoutManager
        binding.fragmentNewsView.adapter = adapterOfNews
    }
}