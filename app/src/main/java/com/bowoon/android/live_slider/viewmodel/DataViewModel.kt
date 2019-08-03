package com.bowoon.android.live_slider.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.bowoon.android.live_slider.data.DataRepository
import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.Item
import com.bowoon.android.live_slider.model.Rss
import com.bowoon.android.live_slider.type.NewsType

class DataViewModel : ViewModel() {
    private var rss: MediatorLiveData<Rss> = MediatorLiveData()

    init {
        rss = DataRepository.mainNews
    }

    fun getRSS(type: NewsType): LiveData<Rss> {
        rss = when (type) {
            NewsType.ALL -> DataRepository.allNews
            NewsType.MAIN -> DataRepository.mainNews
            NewsType.MONEY -> DataRepository.moneyNews
            NewsType.LIFE -> DataRepository.lifeNews
            NewsType.POLITICS -> DataRepository.politicsNews
            NewsType.WORLD -> DataRepository.worldNews
            NewsType.CULTURE -> DataRepository.cultureNews
            NewsType.IT -> DataRepository.itNews
            NewsType.DAILY -> DataRepository.dailyNews
            NewsType.SPORT -> DataRepository.sportNews
            NewsType.STAR -> DataRepository.starNews
            else -> {
                MediatorLiveData()
            }
        }

        return rss
    }

    fun getSearchData(query: String): LiveData<Rss> {
        val searchResult = MediatorLiveData<Rss>()
        val all = ArrayList<Rss>()
        val result = ArrayList<Item>()
        all.add(DataRepository.allNews.value!!)
        all.add(DataRepository.mainNews.value!!)
        all.add(DataRepository.moneyNews.value!!)
        all.add(DataRepository.lifeNews.value!!)
        all.add(DataRepository.politicsNews.value!!)
        all.add(DataRepository.worldNews.value!!)
        all.add(DataRepository.cultureNews.value!!)
        all.add(DataRepository.itNews.value!!)
        all.add(DataRepository.dailyNews.value!!)
        all.add(DataRepository.sportNews.value!!)
        all.add(DataRepository.starNews.value!!)

        for (items in all) {
            for (item in items.channel.item) {
                if (item.title.contains(query) || item.description.contains(query)) {
                    result.add(item)
                }
            }
        }

        searchResult.value = Rss(channel = Channel(item = result))
        return searchResult
    }
}