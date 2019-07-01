package com.bowoon.android.live_slider.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("title")
    val title: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("author")
    val author: String,
    @SerializedName("pubDate")
    val pubDate:String
) : Serializable