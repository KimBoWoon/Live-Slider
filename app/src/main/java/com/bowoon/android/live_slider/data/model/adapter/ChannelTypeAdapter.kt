package com.bowoon.android.live_slider.data.model.adapter

import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.data.model.Channel
import com.bowoon.android.live_slider.data.model.Image
import com.bowoon.android.live_slider.data.model.Item
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

class ChannelTypeAdapter : TypeAdapter<Channel> {
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): Channel {
        val channel = Channel()
        var type: TypeAdapter<*>

        while (reader!!.hasElement()) {
            reader.beginElement()

            when (reader.nextElementName()) {
                "title" -> channel.title = reader.nextTextContent()
                "link" -> channel.link = reader.nextTextContent()
                "language" -> channel.language = reader.nextTextContent()
                "copyright" -> channel.copyright = reader.nextTextContent()
                "pubDate" -> channel.pubDate = reader.nextTextContent()
                "lastBuildDate" -> channel.lastBuildDate = reader.nextTextContent()
                "description" -> channel.description = reader.nextTextContent()
                "image" -> {
                    type = config!!.getTypeAdapter(Image::class.java)
                    channel.image = type.fromXml(reader, config)
                }
                "item" -> {
                    type = config!!.getTypeAdapter(Item::class.java)
                    channel.item.add(type.fromXml(reader, config))
                }
                else -> {
                    Log.i("Channel", "not found xml element")
                }
            }
            reader.endElement()
        }

        return channel
    }

    override fun toXml(
        writer: XmlWriter?,
        config: TikXmlConfig?,
        value: Channel?,
        overridingXmlElementTagName: String?
    ) {

    }
}