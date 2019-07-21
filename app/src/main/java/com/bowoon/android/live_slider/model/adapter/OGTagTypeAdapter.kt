package com.bowoon.android.live_slider.model.adapter

import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.OGTag
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

class OGTagTypeAdapter : TypeAdapter<OGTag> {
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): OGTag {
        val ogTag = OGTag()
        while (reader!!.hasElement()) {
            reader.beginElement()
            when (reader.nextElementName()) {
                "og:type" -> ogTag.type = reader.nextTextContent()
                "og:url" -> ogTag.url = reader.nextTextContent()
                "og:title" -> ogTag.title = reader.nextTextContent()
                "og:description" -> ogTag.type = reader.nextTextContent()
                "og:image" -> ogTag.url = reader.nextTextContent()
                "og:site_name" -> ogTag.title = reader.nextTextContent()
                "og:locale" -> ogTag.type = reader.nextTextContent()
                "article:author" -> ogTag.url = reader.nextTextContent()
                "article:publisher" -> ogTag.title = reader.nextTextContent()
                else -> {
                    Log.i("OGTag", "not found xml element")
                }
            }
            reader.endElement()
        }
        return ogTag
    }

    override fun toXml(writer: XmlWriter?, config: TikXmlConfig?, value: OGTag?, overridingXmlElementTagName: String?) {

    }
}