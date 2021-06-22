package com.iit.secondcourse.mobileorganizer.ui.view.addsubtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.iit.secondcourse.mobileorganizer.MobileOrganizerApplication
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.databinding.FragmentNoteAddDialogBinding
import com.iit.secondcourse.mobileorganizer.databinding.FragmentSubtaskAddDialogBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.task.TaskViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.ui.view.addnote.NoteAddDialogFragment
import com.iit.secondcourse.mobileorganizer.utils.SUBTASK_BUNDLE_CONTENT_KEY
import com.iit.secondcourse.mobileorganizer.utils.SUBTASK_BUNDLE_TITLE_KEY
import com.iit.secondcourse.mobileorganizer.utils.SUBTASK_REQUEST_KEY
import java.util.*

class SubtaskAddDialogFragment : BottomSheetDialogFragment() {

    //view binding
    private var _binding: FragmentSubtaskAddDialogBinding? = null
    private val binding get() = _binding!!

    //view model
    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory(((activity as MainActivity).application as MobileOrganizerApplication).taskRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubtaskAddDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet = d.findViewById<View>(R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBehavior.peekHeight = FrameLayout.LayoutParams.MATCH_PARENT
        }

        setListeners()
    }

    private fun setListeners() {
        with(binding) {
            fsadBtnAdd.setOnClickListener {
                val title =
                    if (fsadEditTitle.text.isNullOrEmpty() || fsadEditTitle.text.isNullOrBlank()) "Untitled" else fsadEditTitle.text.toString()
                val content =
                    if (fsadEditContent.text.isNullOrEmpty() || fsadEditContent.text.isNullOrBlank()) "" else fsadEditContent.text.toString()
                (requireActivity() as MainActivity).supportFragmentManager.setFragmentResult(
                    SUBTASK_REQUEST_KEY,
                    bundleOf(
                        SUBTASK_BUNDLE_TITLE_KEY to title,
                        SUBTASK_BUNDLE_CONTENT_KEY to content
                    )
                )
                dismiss()
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = SubtaskAddDialogFragment()
    }

}