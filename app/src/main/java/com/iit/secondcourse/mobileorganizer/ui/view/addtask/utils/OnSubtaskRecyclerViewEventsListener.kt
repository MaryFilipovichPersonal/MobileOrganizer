package com.iit.secondcourse.mobileorganizer.ui.view.addtask.utils

import com.iit.secondcourse.mobileorganizer.data.entities.Subtask

interface OnSubtaskRecyclerViewEventsListener {
    fun onSubtaskItemClick(id: Long)
    fun onSubtaskItemSwiped(subtask: Subtask)
}