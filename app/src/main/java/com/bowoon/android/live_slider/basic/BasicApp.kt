package com.bowoon.android.live_slider.basic

import android.app.Application
import com.bumptech.glide.Glide

class BasicApp : Application() {
    override fun onCreate() {
        super.onCreate()
        app = applicationContext as BasicApp
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Glide.get(this).clearMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Glide.get(this).trimMemory(level)
    }

    companion object {
        lateinit var app: BasicApp
    }
}