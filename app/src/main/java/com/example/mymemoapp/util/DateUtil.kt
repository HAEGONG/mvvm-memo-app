package com.example.mymemoapp.util

import java.text.SimpleDateFormat
import java.util.*

class DateUtil {
    fun getCurrentDate() : String {
        val tz = TimeZone.getTimeZone("Asia/Tokyo")
        val gc = GregorianCalendar(tz).timeInMillis
        val df = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.JAPAN)

        return df.format(gc)
    }
}