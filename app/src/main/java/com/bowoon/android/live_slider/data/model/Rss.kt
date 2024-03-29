package com.bowoon.android.live_slider.data.model

import com.tickaroo.tikxml.annotation.Attribute
import com.tickaroo.tikxml.annotation.Element
import com.tickaroo.tikxml.annotation.Xml

@Xml
data class Rss(
    @Attribute
    var version: String,
    @Element
    var channel: Channel
) {
    constructor() : this("", Channel())
    constructor(channel: Channel) : this("", channel)
}