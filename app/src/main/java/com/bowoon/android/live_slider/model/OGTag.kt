package com.bowoon.android.live_slider.model

import com.tickaroo.tikxml.annotation.PropertyElement
import java.io.Serializable

data class OGTag(
    @PropertyElement
    var type: String?,
    @PropertyElement
    var url: String?,
    @PropertyElement
    var title: String?,
    @PropertyElement
    var description: String?,
    @PropertyElement
    var image: String?,
    @PropertyElement
    var siteName: String?,
    @PropertyElement
    var locale: String?,
    @PropertyElement
    var author: String?,
    @PropertyElement
    var publisher: String?
) : Serializable {
    constructor() : this("", "", "", "", "", "", "", "", "")
}