package com.iit.secondcourse.mobileorganizer.data.db.utils

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long): Calendar {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = value
        return calendar
    }

    @TypeConverter
    fun dateToTimestamp(date: Calendar): Long {
        return date.timeInMillis
    }
}