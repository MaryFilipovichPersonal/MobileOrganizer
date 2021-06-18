package com.iit.secondcourse.mobileorganizer.ui.view.addnote

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.iit.secondcourse.mobileorganizer.MobileOrganizerApplication
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.databinding.FragmentNoteAddDialogBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.utils.NOTE_ID
import java.util.*

class NoteAddDialogFragment : BottomSheetDialogFragment() {

    //view binding
    private var _binding: FragmentNoteAddDialogBinding? = null
    private val binding get() = _binding!!

    //view model
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(((activity as MainActivity).application as MobileOrganizerApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteAddDialogBinding.inflate(inflater, container, false)
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

//        arguments?.let {
//            param1 = it.getLong(NOTE_ID)
//        }
    }

    private fun setListeners() {
        with(binding) {
            fnadBtnAdd.setOnClickListener {
                val title =
                    if (fnadEditTitle.text.isNullOrEmpty() || fnadEditTitle.text.isNullOrBlank()) "Untitled" else fnadEditTitle.text.toString()
                val content =
                    if (fnadEditContent.text.isNullOrEmpty() || fnadEditContent.text.isNullOrBlank()) "" else fnadEditContent.text.toString()
                noteViewModel.insertNote(
                    Note(
                        title,
                        content,
                        Calendar.getInstance(),
                        Calendar.getInstance()
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
        fun newInstance(/*id: Long*/): NoteAddDialogFragment =
            NoteAddDialogFragment().apply {
//                arguments = Bundle().apply {
//                    putLong(NOTE_ID, id)
//                }
            }

    }
}