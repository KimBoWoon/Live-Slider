package com.bowoon.android.live_slider.data.model

data class NewsSetting(
    var allNews: Boolean,
    var moneyNews: Boolean,
    var lifeNews: Boolean,
    var politicsNews: Boolean,
    var worldNews: Boolean,
    var cultureNews: Boolean,
    var itNews: Boolean,
    var dailyNews: Boolean,
    var sportNews: Boolean,
    var starNews: Boolean
) {
    constructor() : this(false, false, false, false, false, false, false, false, false,false)
}