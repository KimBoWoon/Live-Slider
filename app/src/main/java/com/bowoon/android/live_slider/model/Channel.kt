package com.bowoon.android.live_slider.model

import com.google.gson.annotations.SerializedName

data class Channel(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("language")
    val language: String,
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("pubDate")
    val pubDate: String,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String,
    @SerializedName("item")
    val item: ArrayList<Item>
)