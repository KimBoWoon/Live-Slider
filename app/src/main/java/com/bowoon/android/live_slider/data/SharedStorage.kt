package com.bowoon.android.live_slider.data

import android.content.Context
import com.bowoon.android.live_slider.basic.BasicApp

object SharedStorage {
    private val pref = BasicApp.app.getSharedPreferences("live-slider", Context.MODE_PRIVATE)

    fun setBoolean(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun setString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun setInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    fun setLong(key: String, value: Long) {
        pref.edit().putLong(key, value).apply()
    }

    fun setFloat(key: String, value: Float) {
        pref.edit().putFloat(key, value).apply()
    }

    fun getBoolean(key: String): Boolean {
        return pref.getBoolean(key, true)
    }

    fun getString(key: String): String? {
        return pref.getString(key, "NOT_FOUND")
    }

    fun getInt(key: String): Int {
        return pref.getInt(key, Int.MIN_VALUE)
    }

    fun getLong(key: String): Long {
        return pref.getLong(key, Long.MIN_VALUE)
    }

    fun getFloat(key: String): Float {
        return pref.getFloat(key, Float.MIN_VALUE)
    }
}