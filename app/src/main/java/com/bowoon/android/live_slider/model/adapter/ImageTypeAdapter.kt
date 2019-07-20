package com.bowoon.android.live_slider.model.adapter

import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Image
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

class ImageTypeAdapter : TypeAdapter<Image> {
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): Image {
        val image: Image = Image()
        while (reader!!.hasElement()) {
            reader.beginElement()
            Log.i("TypeAdapter", reader.nextElementName())
            Log.i("image", reader.nextTextContent())
            reader.endElement()
        }
        return image
    }

    override fun toXml(writer: XmlWriter?, config: TikXmlConfig?, value: Image?, overridingXmlElementTagName: String?) {

    }
}