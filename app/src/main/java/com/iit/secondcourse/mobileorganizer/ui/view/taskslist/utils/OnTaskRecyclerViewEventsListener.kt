package com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils

import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks

interface OnTaskRecyclerViewEventsListener {
    fun onTaskItemClick(id: Long)
    fun onTaskItemSwiped(task: TaskWithSubtasks)
}