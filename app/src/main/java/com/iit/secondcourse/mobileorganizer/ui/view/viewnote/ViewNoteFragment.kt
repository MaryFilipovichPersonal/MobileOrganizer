package com.iit.secondcourse.mobileorganizer.ui.view.viewnote

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.iit.secondcourse.mobileorganizer.MobileOrganizerApplication
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.databinding.FragmentViewNoteBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.utils.NOTE_ID
import java.util.*

class ViewNoteFragment : Fragment() {

    //view binding
    private var _binding: FragmentViewNoteBinding? = null
    private val binding get() = _binding!!

    //view model
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(((activity as MainActivity).application as MobileOrganizerApplication).repository)
    }

    //system back btn handling
    private var onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (checkEditTitle() || checkEditContent()) {
                val dialog = AlertDialog.Builder(requireContext())

                dialog.apply {
                    setTitle("Warning")
                    setMessage("Are you sure in saving updates of the note?")
                    setPositiveButton("Save") { _, _ ->
                        updateNote()
                    }
                    setNegativeButton("Cancel") { _, _ ->
                        requireActivity().supportFragmentManager.popBackStack()
                    }
                }.create().show()
            } else
                requireActivity().supportFragmentManager.popBackStack()
        }
    }

    private var noteId: Long = 0
    private lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            noteId = it.getLong(NOTE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getNote()

        setObservers()

        setListeners()
    }

    private fun setObservers() {
        noteViewModel.noteFromId.observe(viewLifecycleOwner, {
            it?.let {
                note = it
                setData()
            }
        })
        noteViewModel.isUpdated.observe(viewLifecycleOwner, { isUpdated ->
            if (isUpdated) {
                noteViewModel.setUpdatingProcess(false)
                requireActivity().supportFragmentManager.popBackStack()
            }

        })
    }

    private fun getNote() {
        noteViewModel.getNoteById(noteId)
    }

    private fun setData() {
        binding.apply {
            fvnEditTitle.setText(note.title)
            fvnEditContent.setText(note.content)
        }
    }

    private fun setListeners() {
        binding.apply {
            fvnEditTitle.addTextChangedListener {
                fvnBtnSave.isEnabled = checkEditTitle()
            }
            fvnEditContent.addTextChangedListener {
                fvnBtnSave.isEnabled = checkEditContent()
            }
            fvnBtnSave.setOnClickListener {
                updateNote()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            requireActivity(),
            onBackPressedCallback
        )
    }

    private fun updateNote() {
        with(binding) {
            val title =
                if (fvnEditTitle.text.isNullOrEmpty() || fvnEditTitle.text.isNullOrBlank()) "Untitled" else fvnEditTitle.text.toString()
            val content =
                if (fvnEditContent.text.isNullOrEmpty() || fvnEditContent.text.isNullOrBlank()) "" else fvnEditContent.text.toString()
            noteViewModel.setUpdatingProcess(false)
            val newNote = note.copy(title = title, content = content, dateUpdate = Calendar.getInstance())
            newNote.id = noteId
            noteViewModel.updateNote(newNote)
        }
    }

    private fun checkEditTitle() =
        with(binding) { !fvnEditTitle.text.isNullOrEmpty() && !fvnEditTitle.text.isNullOrBlank() && fvnEditTitle.text.toString() != note.title }

    private fun checkEditContent() =
        with(binding) { !fvnEditContent.text.isNullOrEmpty() && !fvnEditContent.text.isNullOrBlank() && fvnEditContent.text.toString() != note.content }

    companion object {
        @JvmStatic
        fun newInstance(noteId: Long) =
            ViewNoteFragment().apply {
                arguments = Bundle().apply {
                    putLong(NOTE_ID, noteId)
                }
            }
    }

    override fun onDestroyView() {
        onBackPressedCallback.isEnabled = false
        onBackPressedCallback.remove()
        _binding = null
        super.onDestroyView()
    }

}