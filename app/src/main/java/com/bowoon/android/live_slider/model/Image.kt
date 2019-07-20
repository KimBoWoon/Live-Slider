package com.bowoon.android.live_slider.model

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml(name = "image")
data class Image(
    @Path("/rss/channel/image/title")
    @PropertyElement
    var title: String,
    @Path("/rss/channel/image/url")
    @PropertyElement
    var url: String,
    @Path("/rss/channel/image/link")
    @PropertyElement
    var link: String
) {
    constructor() : this("", "", "")
}