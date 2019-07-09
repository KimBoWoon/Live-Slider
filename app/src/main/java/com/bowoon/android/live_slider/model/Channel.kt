package com.bowoon.android.live_slider.model

import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("title")
    var title: String,
    @SerializedName("link")
    var link: String,
    @SerializedName("language")
    var language: String,
    @SerializedName("copyright")
    var copyright: String,
    @SerializedName("pubDate")
    var pubDate: String,
    @SerializedName("lastBuildDate")
    var lastBuildDate: String,
    @SerializedName("item")
    var item: ArrayList<Item>
) {
    constructor() : this("", "", "", "", "", "", ArrayList<Item>())
}