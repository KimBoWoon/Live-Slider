package com.bowoon.android.live_slider.model

import com.tickaroo.tikxml.annotation.PropertyElement
import java.io.Serializable

data class Item(
    @PropertyElement
    var title: String,
    @PropertyElement
    var link: String,
    @PropertyElement
    var description: String,
    @PropertyElement
    var author: String,
    @PropertyElement
    var pubDate: String,
    var ogTag: OGTag = OGTag()
) : Serializable {
    constructor() : this("", "", "", "", "", OGTag())
}