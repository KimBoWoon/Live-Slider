package com.bowoon.android.live_slider.model.adapter

import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Channel
import com.bowoon.android.live_slider.model.Image
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

class ChannelTypeAdapter : TypeAdapter<Channel> {
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): Channel {
        val channel: Channel = Channel()

        Log.i("Channel", reader!!.path)
        while (reader.hasElement()) {
            reader.beginElement()
            Log.i("Channel", reader.nextElementName())
            Log.i("Channel", reader.nextTextContent())
            val type = config!!.getTypeAdapter(Image::class.java)
            channel.image = type.fromXml(reader, config)
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