package com.bowoon.android.live_slider.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.bowoon.android.live_slider.data.DataRepository
import com.bowoon.android.live_slider.model.Rss

class MajorNewsViewModel : ViewModel() {
    private var rss: MediatorLiveData<Rss> = MediatorLiveData()

    init {
        rss = DataRepository.mainNews
    }

    fun getRSS(): LiveData<Rss> {
        return rss
    }
}