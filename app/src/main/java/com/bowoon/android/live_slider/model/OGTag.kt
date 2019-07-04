package com.bowoon.android.live_slider.model

import com.google.gson.annotations.SerializedName

data class OGTag(
    @SerializedName("og:type")
    var type: String,
    @SerializedName("og:url")
    var url: String,
    @SerializedName("og:title")
    var title: String,
    @SerializedName("og:description")
    var description: String,
    @SerializedName("og:image")
    var image: String,
    @SerializedName("og:site_name")
    var siteName: String,
    @SerializedName("og:locale")
    var locale: String,
    @SerializedName("article:author")
    var author: String,
    @SerializedName("article:publisher")
    var publisher: String
) {
    constructor() : this("", "", "", "", "", "", "", "", "")
}