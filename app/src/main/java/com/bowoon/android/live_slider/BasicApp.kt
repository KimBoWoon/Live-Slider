package com.bowoon.android.live_slider

import android.app.Application
import com.bowoon.android.live_slider.model.Item
import com.bumptech.glide.Glide

class BasicApp : Application() {
    companion object {
        val newsItems: ArrayList<Item> = ArrayList<Item>()
        val mainNewsItems: ArrayList<Item> = ArrayList<Item>()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }
}