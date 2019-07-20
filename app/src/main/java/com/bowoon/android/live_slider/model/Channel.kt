package com.bowoon.android.live_slider.model

import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Channel(
    @PropertyElement
    var title: String,
    @PropertyElement
    var link: String,
    @PropertyElement
    var language: String,
    @PropertyElement
    var copyright: String,
    @PropertyElement
    var pubDate: String,
    @PropertyElement
    var lastBuildDate: String,
    @PropertyElement
    var description: String,
    @Element
    var image: Image,
    @PropertyElement
    var item: ArrayList<Item>
) {
    constructor() : this("", "", "", "", "", "", "", Image(), ArrayList<Item>())
}