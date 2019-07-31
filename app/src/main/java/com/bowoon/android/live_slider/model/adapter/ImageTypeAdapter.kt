package com.bowoon.android.live_slider.model.adapter

import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Image
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

class ImageTypeAdapter : TypeAdapter<Image> {
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): Image {
        val image = Image()
        while (reader!!.hasElement()) {
            reader.beginElement()
            when (reader.nextElementName()) {
                "title" -> image.title = reader.nextTextContent()
                "url" -> image.url = reader.nextTextContent()
                "link" -> image.link = reader.nextTextContent()
                else -> {
                    Log.i("Image", "not found xml element")
                }
            }
            reader.endElement()
        }
        return image
    }

    override fun toXml(writer: XmlWriter?, config: TikXmlConfig?, value: Image?, overridingXmlElementTagName: String?) {

    }
}