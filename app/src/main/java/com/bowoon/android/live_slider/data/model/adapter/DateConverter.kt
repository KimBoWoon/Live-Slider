package com.bowoon.android.live_slider.data.model.adapter

import com.tickaroo.tikxml.TypeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class DateConverter : TypeConverter<DateTime> {
    private val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")

    @Throws(Exception::class)
    override fun read(value: String): DateTime {
        return formatter.parseDateTime(value)
    }

    @Throws(Exception::class)
    override fun write(value: DateTime): String {
        return formatter.print(value)
    }
}