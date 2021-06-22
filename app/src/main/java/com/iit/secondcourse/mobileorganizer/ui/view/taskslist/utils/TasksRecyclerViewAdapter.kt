package com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import at.grabner.circleprogress.CircleProgressView
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.utils.DateUtils
import com.iit.secondcourse.mobileorganizer.utils.SwipeToDeleteCallback

class TasksRecyclerViewAdapter(private val listener: OnTaskRecyclerViewEventsListener) :
    RecyclerView.Adapter<TaskViewHolder>(), SwipeToDeleteCallback.ItemTouchHelperAdapter {

    private var tasksWithSubtasks: List<TaskWithSubtasks> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_task, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasksWithSubtasks[position]
        holder.bind(task)
        holder.itemView.setOnClickListener {
            listener.onTaskItemClick(task.task.id)
        }
    }

    override fun getItemCount() = tasksWithSubtasks.size

    fun submitList(newList: List<TaskWithSubtasks>) {
        if (tasksWithSubtasks != newList) {
            val diffUtilsCallback = TasksDiffUtilsCallback(tasksWithSubtasks, newList)
            val diffResult = DiffUtil.calculateDiff(diffUtilsCallback)
            diffResult.dispatchUpdatesTo(this)
            tasksWithSubtasks = newList
        } else {
            tasksWithSubtasks = newList
            notifyDataSetChanged()
        }
    }

    override fun onRowSwiped(position: Int) {
        listener.onTaskItemSwiped(tasksWithSubtasks[position])
    }
}

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvTaskTitle: TextView = itemView.findViewById(R.id.vht_tv_task_title)
    private var tvTaskDescription: TextView = itemView.findViewById(R.id.vht_tv_task_description)
    private var tvTaskDateStart: TextView = itemView.findViewById(R.id.vht_tv_task_start)
    private var tvTaskDateDeadline: TextView = itemView.findViewById(R.id.vht_tv_task_deadline)
    private var cpvTaskProgress: CircleProgressView =
        itemView.findViewById(R.id.vht_cpv_progress_bar)
    private var tvCategory: TextView = itemView.findViewById(R.id.vht_tv_task_category)

    fun bind(taskWithSubtasks: TaskWithSubtasks) {
        with(taskWithSubtasks) {
            tvTaskTitle.text = context.getString(R.string.title, task.title)
            tvTaskDescription.text = context.getString(R.string.content, task.description)
            tvTaskDateStart.text = context.getString(
                R.string.date_start,
                DateUtils.getTaskFormattedDate(task.dateStart)
            )
            tvTaskDateDeadline.text = context.getString(
                R.string.date_deadline,
                DateUtils.getTaskFormattedDate(task.dateDeadline)
            )
            val progress = TaskProgressCalculation.getProgressFromCompletedSubtasksNum(
                subtasks.size,
                subtasks.filter { it.isCompleted }.size
            )
            cpvTaskProgress.setValueAnimated(progress)

        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context