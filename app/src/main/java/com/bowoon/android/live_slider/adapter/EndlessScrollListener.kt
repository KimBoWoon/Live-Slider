package com.bowoon.android.live_slider.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bowoon.android.live_slider.data.DataRepository
import com.bowoon.android.live_slider.data.type.NewsType
import com.bowoon.android.live_slider.data.type.NewsType.*
import com.bowoon.android.live_slider.data.model.Item

object EndlessScrollListener : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }

    fun onLoadMore(position: Int, type: NewsType, list: ArrayList<Item>) {
        when (type) {
            ALL -> list.addAll(DataRepository.allNews.value!!.channel.item.subList(position, position + 5))
            MAIN -> list.addAll(DataRepository.mainNews.value!!.channel.item.subList(position, position + 5))
            MONEY -> list.addAll(DataRepository.moneyNews.value!!.channel.item.subList(position, position + 5))
            LIFE -> list.addAll(DataRepository.lifeNews.value!!.channel.item.subList(position, position + 5))
            POLITICS -> list.addAll(DataRepository.politicsNews.value!!.channel.item.subList(position, position + 5))
            WORLD -> list.addAll(DataRepository.worldNews.value!!.channel.item.subList(position, position + 5))
            CULTURE -> list.addAll(DataRepository.cultureNews.value!!.channel.item.subList(position, position + 5))
            IT -> list.addAll(DataRepository.itNews.value!!.channel.item.subList(position, position + 5))
            DAILY -> list.addAll(DataRepository.dailyNews.value!!.channel.item.subList(position, position + 5))
            SPORT -> list.addAll(DataRepository.sportNews.value!!.channel.item.subList(position, position + 5))
            STAR -> list.addAll(DataRepository.starNews.value!!.channel.item.subList(position, position + 5))
            SEARCH -> {}
        }

        DataRepository.ogTagRequest(list)
    }

    fun onLoadMore(fromPosition: Int, toPosition: Int, type: NewsType, list: ArrayList<Item>) {
        when (type) {
            ALL -> list.addAll(DataRepository.allNews.value!!.channel.item.subList(fromPosition, toPosition))
            MAIN -> list.addAll(DataRepository.mainNews.value!!.channel.item.subList(fromPosition, toPosition))
            MONEY -> list.addAll(DataRepository.moneyNews.value!!.channel.item.subList(fromPosition, toPosition))
            LIFE -> list.addAll(DataRepository.lifeNews.value!!.channel.item.subList(fromPosition, toPosition))
            POLITICS -> list.addAll(DataRepository.politicsNews.value!!.channel.item.subList(fromPosition, toPosition))
            WORLD -> list.addAll(DataRepository.worldNews.value!!.channel.item.subList(fromPosition, toPosition))
            CULTURE -> list.addAll(DataRepository.cultureNews.value!!.channel.item.subList(fromPosition, toPosition))
            IT -> list.addAll(DataRepository.itNews.value!!.channel.item.subList(fromPosition, toPosition))
            DAILY -> list.addAll(DataRepository.dailyNews.value!!.channel.item.subList(fromPosition, toPosition))
            SPORT -> list.addAll(DataRepository.sportNews.value!!.channel.item.subList(fromPosition, toPosition))
            STAR -> list.addAll(DataRepository.starNews.value!!.channel.item.subList(fromPosition, toPosition))
            SEARCH -> {}
        }

        DataRepository.ogTagRequest(list)
    }
}