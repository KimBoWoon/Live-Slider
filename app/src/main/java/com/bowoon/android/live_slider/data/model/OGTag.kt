package com.bowoon.android.live_slider.data.model

import pl.droidsonroids.jspoon.annotation.Selector
import java.io.Serializable

data class OGTag(
    @Selector("meta[property=og:type]", attr = "content")
    var type: String?,
    @Selector("meta[property=og:url]", attr = "content")
    var url: String?,
    @Selector("meta[property=og:title]", attr = "content")
    var title: String?,
    @Selector("meta[property=og:description]", attr = "content")
    var description: String?,
    @Selector("meta[property=og:image]", attr = "content")
    var image: String?,
    @Selector("meta[property=og:site_name]", attr = "content")
    var siteName: String?,
    @Selector("meta[property=og:locale]", attr = "content")
    var locale: String?,
    @Selector("meta[property=article:author]", attr = "content")
    var author: String?,
    @Selector("meta[property=article:publisher]", attr = "content")
    var publisher: String?
) : Serializable {
    constructor() : this("", "", "", "", "", "", "", "", "")
}