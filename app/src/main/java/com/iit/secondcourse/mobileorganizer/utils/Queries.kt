package com.iit.secondcourse.mobileorganizer.utils

object Queries {
    const val getNotesUpdateOrder =
        "SELECT * FROM $NOTE_TABLE_NAME ORDER BY $NOTE_DATE_UPDATE DESC"

}