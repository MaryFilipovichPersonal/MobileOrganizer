package com.iit.secondcourse.mobileorganizer.ui.view.addnote

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.utils.NOTE_ID

class NoteAddDialogFragment : BottomSheetDialogFragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_note_add_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }


    companion object {
        fun newInstance(id: Long): NoteAddDialogFragment =
            NoteAddDialogFragment().apply {
                arguments = Bundle().apply {
                    putLong(NOTE_ID, id)
                }
            }

    }
}