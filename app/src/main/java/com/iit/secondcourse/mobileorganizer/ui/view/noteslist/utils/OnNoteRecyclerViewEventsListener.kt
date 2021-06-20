package com.iit.secondcourse.mobileorganizer.ui.view.noteslist.utils

import com.iit.secondcourse.mobileorganizer.data.entities.Note

interface OnNoteRecyclerViewEventsListener {
    fun onNoteItemClick(id: Long)
    fun onNoteItemSwiped(note: Note)
}