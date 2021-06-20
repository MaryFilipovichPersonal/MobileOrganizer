package com.iit.secondcourse.mobileorganizer.ui.view.taskslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.iit.secondcourse.mobileorganizer.MobileOrganizerApplication
import com.iit.secondcourse.mobileorganizer.data.db.MobileOrganizerDatabase
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.databinding.FragmentTasksListBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils.TasksRecyclerViewAdapter
import com.iit.secondcourse.mobileorganizer.utils.NOTES_LIST_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.OnRecyclerViewEventsListener
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
    private val itemClickListener = object : OnRecyclerViewEventsListener {
        override fun onItemClick(id: Long) {
            Log.d(NOTES_LIST_FRAGMENT, "OnCardClick")
        }

        override fun onItemSwiped(note: Note) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksListBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerViewAdapter()

        setListeners()

        setTasksObservers()
    }

    private fun setRecyclerViewAdapter() {
        adapter = TasksRecyclerViewAdapter(itemClickListener)
        binding.ftlRvTasksList.adapter = adapter
    }

    private fun setListeners() {

    }

    private fun setTasksObservers() {
        taskViewModel.allTasks.observe(viewLifecycleOwner, { tasks ->
            Log.d(TASKS_LIST_FRAGMENT, "allTasks.observe: tasks = $tasks")
            tasks?.let{ adapter.submitList(it) }
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