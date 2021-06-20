package com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils

import androidx.recyclerview.widget.DiffUtil
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks

class TasksDiffUtilsCallback(
    private val oldList: List<TaskWithSubtasks>,
    private val newList: List<TaskWithSubtasks>
) : DiffUtil.Callback(){

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]
        return oldItem.task.id == newItem.task.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[oldItemPosition]
        return oldItem == newItem
    }

}