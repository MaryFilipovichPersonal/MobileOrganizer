package com.iit.secondcourse.mobileorganizer.utils



import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun getFormattedDate (calendar: Calendar): String {
        return SimpleDateFormat("dd.MM.yyyy HH:mm").format(calendar.time).toString()
    }
}