package com.bowoon.android.live_slider.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.BasicApp
import com.bowoon.android.live_slider.Data
import com.bowoon.android.live_slider.model.Item

object EndlessScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    fun onMainNewsLoadMore(position: Int, list: ArrayList<Item>) {
        list.addAll(Data.mainNewsItems.subList(position, position + 5))
    }

    fun onMainNewsLoadMore(fromPosition: Int, toPosition: Int, list: ArrayList<Item>) {
        list.addAll(Data.mainNewsItems.subList(fromPosition, toPosition))
    }

    fun onNewsLoadMore(position: Int, list: ArrayList<Item>) {
        list.addAll(Data.newsItems.subList(position, position + 5))
    }

    fun onNewsLoadMore(fromPosition: Int, toPosition: Int, list: ArrayList<Item>) {
        list.addAll(Data.newsItems.subList(fromPosition, toPosition))
    }
}