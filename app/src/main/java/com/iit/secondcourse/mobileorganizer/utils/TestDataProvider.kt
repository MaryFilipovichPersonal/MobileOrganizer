package com.iit.secondcourse.mobileorganizer.utils

import com.iit.secondcourse.mobileorganizer.data.entities.Note
import java.util.*

object TestDataProvider {

    fun getNotes(): List<Note> = arrayListOf(
        Note("Title 1", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 2", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 3", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 4", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 5", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 6", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 7", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 8", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 9", "some content", Calendar.getInstance(), Calendar.getInstance())
    )

}