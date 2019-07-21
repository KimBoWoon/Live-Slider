package com.bowoon.android.live_slider.model.adapter

import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.Rss
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

class RssTypeAdapter : TypeAdapter<Rss> {
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): Rss {
        val rss = Rss()
        reader!!.nextAttributeName()
        rss.version = reader.nextAttributeValue()
        if (reader.hasElement()) {
            reader.beginElement()
            reader.nextElementName()
            val type = config!!.getTypeAdapter(Channel::class.java)
            rss.channel = type.fromXml(reader, config)
            reader.endElement()
        }
        return rss
    }

    override fun toXml(writer: XmlWriter?, config: TikXmlConfig?, value: Rss?, overridingXmlElementTagName: String?) {

    }
}