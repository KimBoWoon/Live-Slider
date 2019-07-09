package com.bowoon.android.live_slider.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item(
    @SerializedName("title")
    var title: String,
    @SerializedName("link")
    var link: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("author")
    var author: String,
    @SerializedName("pubDate")
    var pubDate: String,
    var ogTag: OGTag = OGTag()
) : Serializable {
    constructor() : this("", "", "", "", "", OGTag())
}