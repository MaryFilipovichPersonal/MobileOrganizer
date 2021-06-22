package com.iit.secondcourse.mobileorganizer.ui.view.addtask

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import com.iit.secondcourse.mobileorganizer.MobileOrganizerApplication
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.databinding.FragmentTaskAddBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.ui.view.addsubtask.SubtaskAddDialogFragment
import com.iit.secondcourse.mobileorganizer.ui.view.addtask.utils.OnSubtaskRecyclerViewEventsListener
import com.iit.secondcourse.mobileorganizer.ui.view.addtask.utils.SubtaskRecyclerViewAdapter
import com.iit.secondcourse.mobileorganizer.utils.*
import java.util.*

class TaskAddFragment : Fragment() {

    //view binding
    private var _binding: FragmentTaskAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SubtaskRecyclerViewAdapter

    //view model
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(((activity as MainActivity).application as MobileOrganizerApplication).taskRepository)
    }

    //card listener
    private val itemClickListener = object : OnSubtaskRecyclerViewEventsListener {
        override fun onSubtaskItemClick(id: Long) {

        }

        override fun onSubtaskItemSwiped(subtask: Subtask) {}
    }

    //date start set listener
    private val dateStartSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateStart.set(Calendar.YEAR, year)
            dateStart.set(Calendar.MONTH, monthOfYear)
            dateStart.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.ftaTvDateStart.text = requireContext().getString(
                R.string.date_start,
                DateUtils.getTaskFormattedDate(dateStart)
            )
            isDateStartSet = true
        }

    //date deadline listener
    private val dateDeadlineSetListener =
        DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            dateDeadline.set(Calendar.YEAR, year)
            dateDeadline.set(Calendar.MONTH, monthOfYear)
            dateDeadline.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            binding.ftaTvDateDeadline.text = requireContext().getString(
                R.string.date_deadline,
                DateUtils.getTaskFormattedDate(dateDeadline)
            )
            isDateDeadlineSet = true
        }

    private var isDateStartSet = false
    private var isDateDeadlineSet = false
    private val calendar = Calendar.getInstance()
    private var dateStart = Calendar.getInstance()
    private var dateDeadline = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewAdapter()

        setListeners()

        setTaskObservers()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun setRecyclerViewAdapter() {
        adapter = SubtaskRecyclerViewAdapter(itemClickListener)
        binding.ftaRvSubtasksList.adapter = adapter
        val callback: ItemTouchHelper.Callback = SwipeToDeleteCallback(adapter, ItemTouchHelper.END)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.ftaRvSubtasksList)
    }

    private fun setListeners() {
        with(binding) {
            ftaBtnAddTask.setOnClickListener {
                addTask()
            }
            ftaTvDateStart.setOnClickListener {
                showDatePickerDialog(false)
            }
            ftaTvDateDeadline.setOnClickListener {
                showDatePickerDialog(true)
            }
            ftaBtnAddSubtask.setOnClickListener {
                showSubtaskAddFragment()
            }
            (requireActivity() as MainActivity).supportFragmentManager.setFragmentResultListener(
                SUBTASK_REQUEST_KEY, viewLifecycleOwner
            ) { key, bundle ->
                val title: String = bundle.getString(SUBTASK_BUNDLE_TITLE_KEY).toString()
                val content = bundle.getString(SUBTASK_BUNDLE_CONTENT_KEY).toString()
                adapter.addSubtask(Subtask(0, title, content, false))
            }
        }
    }

    private fun showSubtaskAddFragment() {
        val subtaskAddDialogFragment = SubtaskAddDialogFragment.newInstance()
        subtaskAddDialogFragment.show(
            (activity as MainActivity).supportFragmentManager,
            ADD_SUBTASK_FRAGMENT
        )
    }

    private fun showDatePickerDialog(isDeadline: Boolean) {
        DatePickerDialog(
            requireActivity(),
            if (isDeadline) dateDeadlineSetListener else dateStartSetListener,
            // set DatePickerDialog to point to today's date when it loads up
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun addTask() {
        with(binding) {
            if (checkDates() && adapter.itemCount > 0) {
                val title =
                    if (ftaEtTitle.text.isNullOrEmpty() || ftaEtTitle.text.isNullOrBlank()) "Untitled" else ftaEtTitle.text.toString()
                val content =
                    if (ftaEtContent.text.isNullOrEmpty() || ftaEtContent.text.isNullOrBlank()) "" else ftaEtContent.text.toString()
                taskViewModel.insertTask(
                    title,
                    content,
                    dateStart,
                    dateDeadline,
                    adapter.getSubtasks()
                )
                requireActivity().supportFragmentManager.popBackStack()
            } else {
                Toast.makeText(
                    requireContext(),
                    "It is necessary to set dates and create at least one subtask",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun checkDates() = (isDateStartSet && isDateDeadlineSet)

    private fun setTaskObservers() {
    }

    companion object {
        fun newInstance() = TaskAddFragment()
    }

}