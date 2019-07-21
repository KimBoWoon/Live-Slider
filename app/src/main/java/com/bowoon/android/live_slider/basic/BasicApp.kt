package com.bowoon.android.live_slider.basic

import android.app.Application
import com.bumptech.glide.Glide

class BasicApp : Application() {
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