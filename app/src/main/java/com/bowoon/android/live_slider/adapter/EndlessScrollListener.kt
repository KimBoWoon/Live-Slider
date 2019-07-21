package com.bowoon.android.live_slider.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.data.Data
import com.bowoon.android.live_slider.type.NewsType
import com.bowoon.android.live_slider.type.NewsType.*
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
            MONEY -> list.addAll(Data.moneyNews.subList(position, position + 5))
            LIFE -> list.addAll(Data.lifeNews.subList(position, position + 5))
            POLITICS -> list.addAll(Data.politicsNews.subList(position, position + 5))
            WORLD -> list.addAll(Data.worldNews.subList(position, position + 5))
            CULTURE -> list.addAll(Data.cultureNews.subList(position, position + 5))
            IT -> list.addAll(Data.itNews.subList(position, position + 5))
            DAILY -> list.addAll(Data.dailyNews.subList(position, position + 5))
            SPORT -> list.addAll(Data.sportNews.subList(position, position + 5))
            STAR -> list.addAll(Data.starNews.subList(position, position + 5))
        }
    }

    fun onLoadMore(fromPosition: Int, toPosition: Int, type: NewsType, list: ArrayList<Item>) {
        when (type) {
            ALL -> list.addAll(Data.allNews.subList(fromPosition, toPosition))
            MAIN -> list.addAll(Data.mainNews.subList(fromPosition, toPosition))
            MONEY -> list.addAll(Data.moneyNews.subList(fromPosition, toPosition))
            LIFE -> list.addAll(Data.lifeNews.subList(fromPosition, toPosition))
            POLITICS -> list.addAll(Data.politicsNews.subList(fromPosition, toPosition))
            WORLD -> list.addAll(Data.worldNews.subList(fromPosition, toPosition))
            CULTURE -> list.addAll(Data.cultureNews.subList(fromPosition, toPosition))
            IT -> list.addAll(Data.itNews.subList(fromPosition, toPosition))
            DAILY -> list.addAll(Data.dailyNews.subList(fromPosition, toPosition))
            SPORT -> list.addAll(Data.sportNews.subList(fromPosition, toPosition))
            STAR -> list.addAll(Data.starNews.subList(fromPosition, toPosition))
        }
    }
}