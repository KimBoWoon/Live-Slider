package com.bowoon.android.live_slider.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.bowoon.android.live_slider.model.Rss
import androidx.lifecycle.LiveData
import com.bowoon.android.live_slider.data.DataRepository
import com.bowoon.android.live_slider.type.NewsType

class NewsViewModel : ViewModel() {
    private var rss: MediatorLiveData<Rss> = MediatorLiveData()

    init {
        rss = DataRepository.allNews
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
        }

        return rss
    }
}