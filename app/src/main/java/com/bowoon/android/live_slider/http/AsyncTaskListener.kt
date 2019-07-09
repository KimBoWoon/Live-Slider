package com.bowoon.android.live_slider.http

interface AsyncTaskListener {
    fun startEvent()
    fun onEventCompleted()
    fun onEventFailed()
}