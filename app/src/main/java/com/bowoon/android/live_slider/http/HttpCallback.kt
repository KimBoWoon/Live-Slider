package com.bowoon.android.live_slider.http

interface HttpCallback {
    fun onSuccess(o: Any)
    fun onFail(o: Any)
}