package com.bowoon.android.live_slider.model.adapter

import com.tickaroo.tikxml.TypeConverter
import java.text.SimpleDateFormat
import java.util.*

class MyDateConverter : TypeConverter<Date> {

    private val formatter = SimpleDateFormat("yyyy.MM.dd") // SimpleDateFormat is not thread safe!

    @Throws(Exception::class)
    override fun read(value: String): Date {
        return formatter.parse(value)
    }

    @Throws(Exception::class)
    override fun write(value: Date): String {
        return formatter.format(value)
    }

}