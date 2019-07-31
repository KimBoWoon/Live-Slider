package com.bowoon.android.live_slider.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.data.DataRepository
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
//            ALL -> list.addAll(DataRepository.allNews.subList(position, position + 5))
//            MAIN -> list.addAll(DataRepository.mainNews.subList(position, position + 5))
//            MONEY -> list.addAll(DataRepository.moneyNews.subList(position, position + 5))
//            LIFE -> list.addAll(DataRepository.lifeNews.subList(position, position + 5))
//            POLITICS -> list.addAll(DataRepository.politicsNews.subList(position, position + 5))
//            WORLD -> list.addAll(DataRepository.worldNews.subList(position, position + 5))
//            CULTURE -> list.addAll(DataRepository.cultureNews.subList(position, position + 5))
//            IT -> list.addAll(DataRepository.itNews.subList(position, position + 5))
//            DAILY -> list.addAll(DataRepository.dailyNews.subList(position, position + 5))
//            SPORT -> list.addAll(DataRepository.sportNews.subList(position, position + 5))
//            STAR -> list.addAll(DataRepository.starNews.subList(position, position + 5))
        }
    }

    fun onLoadMore(fromPosition: Int, toPosition: Int, type: NewsType, list: ArrayList<Item>) {
        when (type) {
//            ALL -> list.addAll(DataRepository.allNews.subList(fromPosition, toPosition))
//            MAIN -> list.addAll(DataRepository.mainNews.subList(fromPosition, toPosition))
//            MONEY -> list.addAll(DataRepository.moneyNews.subList(fromPosition, toPosition))
//            LIFE -> list.addAll(DataRepository.lifeNews.subList(fromPosition, toPosition))
//            POLITICS -> list.addAll(DataRepository.politicsNews.subList(fromPosition, toPosition))
//            WORLD -> list.addAll(DataRepository.worldNews.subList(fromPosition, toPosition))
//            CULTURE -> list.addAll(DataRepository.cultureNews.subList(fromPosition, toPosition))
//            IT -> list.addAll(DataRepository.itNews.subList(fromPosition, toPosition))
//            DAILY -> list.addAll(DataRepository.dailyNews.subList(fromPosition, toPosition))
//            SPORT -> list.addAll(DataRepository.sportNews.subList(fromPosition, toPosition))
//            STAR -> list.addAll(DataRepository.starNews.subList(fromPosition, toPosition))
        }
    }
}