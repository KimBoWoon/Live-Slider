package com.bowoon.android.live_slider.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.BasicApp
import com.bowoon.android.live_slider.model.Item

class EndlessScrollListener : RecyclerView.OnScrollListener() {
    private val items: ArrayList<Item> = ArrayList<Item>()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    fun onLoadMore(position: Int) {
        items.addAll(BasicApp.newsItems.subList(position, position + 5))
    }
}