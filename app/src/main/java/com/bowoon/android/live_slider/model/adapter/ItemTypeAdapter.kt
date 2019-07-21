package com.bowoon.android.live_slider.model.adapter

import com.bowoon.android.live_slider.log.Log
import com.bowoon.android.live_slider.model.Item
import com.tickaroo.tikxml.TikXmlConfig
import com.tickaroo.tikxml.XmlReader
import com.tickaroo.tikxml.XmlWriter
import com.tickaroo.tikxml.typeadapter.TypeAdapter

class ItemTypeAdapter : TypeAdapter<Item> {
    override fun fromXml(reader: XmlReader?, config: TikXmlConfig?): Item {
        val item = Item()
        while (reader!!.hasElement()) {
            reader.beginElement()
            when (reader.nextElementName()) {
                "title" -> item.title = reader.nextTextContent()
                "link" -> item.link = reader.nextTextContent()
                "description" -> item.description = reader.nextTextContent()
                "author" -> item.author = reader.nextTextContent()
                "pubDate" -> item.pubDate = reader.nextTextContent()
                else -> {
                    Log.i("Image", "not found xml element")
                }
            }
            reader.endElement()
        }
        return item
    }

    override fun toXml(
        writer: XmlWriter?,
        config: TikXmlConfig?,
        value: Item?,
        overridingXmlElementTagName: String?
    ) {

    }
}