package com.iit.secondcourse.mobileorganizer.ui.view.taskslist

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.iit.secondcourse.mobileorganizer.MobileOrganizerApplication
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.databinding.FragmentTasksListBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils.TasksRecyclerViewAdapter
import com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils.OnTaskRecyclerViewEventsListener
import com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils.OnTasksListFragmentEventListener
import com.iit.secondcourse.mobileorganizer.utils.SwipeToDeleteCallback
import com.iit.secondcourse.mobileorganizer.utils.TASKS_LIST_FRAGMENT

class TasksListFragment : Fragment() {

    //view binding
    private var _binding: FragmentTasksListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TasksRecyclerViewAdapter

    //view model
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(((activity as MainActivity).application as MobileOrganizerApplication).taskRepository)
    }

    //card listener
    private val itemClickListener = object : OnTaskRecyclerViewEventsListener {
        override fun onTaskItemClick(id: Long) {

        }

        override fun onTaskItemSwiped(task: TaskWithSubtasks) {
            showDeleteDialog(task)
        }
    }

    private var addTaskBtnClickListener: OnTasksListFragmentEventListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapter()

        setListeners()

        setTasksObservers()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnTasksListFragmentEventListener)
            addTaskBtnClickListener = context
    }

    override fun onDetach() {
        addTaskBtnClickListener = null
        super.onDetach()
    }

    private fun showDeleteDialog(task: TaskWithSubtasks) {
        val dialog = AlertDialog.Builder(requireContext())
        dialog.apply {
            setIcon(R.drawable.ic_round_delete_24)
            setTitle("Warning")
            setMessage("Are you sure in deleting the task?")
            setPositiveButton("Delete") {_, _ ->
                taskViewModel.deleteTask(task)
            }
            setNegativeButton("Cancel") {_, _ ->
                taskViewModel.getTasks()?.let { adapter.submitList(it) }
            }
            setOnCancelListener {
                taskViewModel.getTasks()?.let { adapter.submitList(it) }
            }
        }.create().show()
    }

    private fun setRecyclerViewAdapter() {
        adapter = TasksRecyclerViewAdapter(itemClickListener)
        binding.ftlRvTasksList.adapter = adapter
        taskViewModel.getTasks()?.let { adapter.submitList(it) }
        val callback: ItemTouchHelper.Callback = SwipeToDeleteCallback(adapter, ItemTouchHelper.START)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.ftlRvTasksList)
    }

    private fun setListeners() {
        binding.ftlBtnAddTask.setOnClickListener {
            addTaskBtnClickListener?.onAddTaskBtnClick()
        }
    }

    private fun setTasksObservers() {
        taskViewModel.allTasks.observe(viewLifecycleOwner, { tasks ->
            Log.d(TASKS_LIST_FRAGMENT, "allTasks.observe: tasks = $tasks")
            tasks?.let { adapter.submitList(it) }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = TasksListFragment()
    }
}