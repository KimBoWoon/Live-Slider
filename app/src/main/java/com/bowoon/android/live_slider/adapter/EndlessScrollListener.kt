package com.bowoon.android.live_slider.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.Data
import com.bowoon.android.live_slider.NewsType
import com.bowoon.android.live_slider.NewsType.*
import com.bowoon.android.live_slider.model.Item

object EndlessScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    fun onLoadMore(position: Int, type: NewsType, list: ArrayList<Item>) {
        when (type) {
            ALL -> list.addAll(Data.allNews.subList(position, position + 5))
            MAIN -> list.addAll(Data.mainNews.subList(position, position + 5))
            MONEY -> TODO()
            LIFE -> TODO()
            POLITICS -> TODO()
            WORLD -> TODO()
            CULTURE -> TODO()
            IT -> TODO()
            DAILY -> TODO()
        }
    }

    fun onLoadMore(fromPosition: Int, toPosition: Int, type: NewsType, list: ArrayList<Item>) {
        when (type) {
            ALL -> list.addAll(Data.allNews.subList(fromPosition, toPosition))
            MAIN -> list.addAll(Data.mainNews.subList(fromPosition, toPosition))
            MONEY -> TODO()
            LIFE -> TODO()
            POLITICS -> TODO()
            WORLD -> TODO()
            CULTURE -> TODO()
            IT -> TODO()
            DAILY -> TODO()
        }
    }
}