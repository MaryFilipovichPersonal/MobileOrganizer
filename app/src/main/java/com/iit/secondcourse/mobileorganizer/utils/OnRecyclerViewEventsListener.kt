package com.iit.secondcourse.mobileorganizer.utils

import com.iit.secondcourse.mobileorganizer.data.entities.Note

interface OnRecyclerViewEventsListener {
    fun onItemClick(id: Long)
    fun onItemSwiped(note: Note)
}