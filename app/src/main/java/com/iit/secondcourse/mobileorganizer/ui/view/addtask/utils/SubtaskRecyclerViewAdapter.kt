package com.iit.secondcourse.mobileorganizer.ui.view.addtask.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.utils.SwipeToDeleteCallback

class SubtaskRecyclerViewAdapter(private val listener: OnSubtaskRecyclerViewEventsListener) :
    RecyclerView.Adapter<SubtaskViewHolder>(), SwipeToDeleteCallback.ItemTouchHelperAdapter {

    private var subtasks: List<Subtask> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubtaskViewHolder {
        return SubtaskViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_subtask, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubtaskViewHolder, position: Int) {
        val subtask = subtasks[position]
        holder.bind(subtask)
        holder.itemView.setOnClickListener {
            listener.onSubtaskItemClick(subtask.id)
        }
    }

    override fun getItemCount(): Int = subtasks.size

    override fun onRowSwiped(position: Int) {
        val newList: MutableList<Subtask> = subtasks.toMutableList()
        newList.removeAt(position)
        submitList(newList)
    }

    fun submitList(newList: List<Subtask>) {
        if (subtasks != newList) {
            val diffUtilsCallback = SubtasksDiffUtilsCallback(subtasks, newList)
            val diffResult = DiffUtil.calculateDiff(diffUtilsCallback)
            diffResult.dispatchUpdatesTo(this)
            subtasks = newList
        } else {
            subtasks = newList
            notifyDataSetChanged()
        }
    }

    fun getSubtasks() = subtasks

    fun addSubtask(subtask: Subtask) {
        val newList: MutableList<Subtask> = subtasks.toMutableList()
        newList.add(subtask)
        submitList(newList)
    }
}

class SubtaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvSubtaskTitle: TextView = itemView.findViewById(R.id.vhs_tv_subtask_title)
    private var tvSubtaskDescription: TextView = itemView.findViewById(R.id.vhs_subtask_description)
    private var checkboxIsCompleted: MaterialCheckBox =
        itemView.findViewById(R.id.vhs_check_box_complete)

    fun bind(subtask: Subtask) {
        with(subtask) {
            tvSubtaskTitle.text = context.getString(R.string.title, title)
            tvSubtaskDescription.text = context.getString(R.string.content, description)
            checkboxIsCompleted.visibility = View.GONE
        }
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context