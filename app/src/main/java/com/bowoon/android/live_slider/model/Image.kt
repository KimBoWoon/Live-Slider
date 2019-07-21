package com.bowoon.android.live_slider.model

import com.tickaroo.tikxml.annotation.Path
import com.tickaroo.tikxml.annotation.PropertyElement
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Image(
    @PropertyElement
    var title: String,
    @PropertyElement
    var url: String,
    @PropertyElement
    var link: String
) {
    constructor() : this("", "", "")
}