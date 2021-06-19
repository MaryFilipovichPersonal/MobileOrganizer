package com.iit.secondcourse.mobileorganizer.ui.view.noteslist

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
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.databinding.FragmentNotesListBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.ui.view.addnote.NoteAddDialogFragment
import com.iit.secondcourse.mobileorganizer.ui.view.noteslist.utils.NotesRecyclerViewAdapter
import com.iit.secondcourse.mobileorganizer.ui.view.viewnote.utils.OnGoToNoteViewService
import com.iit.secondcourse.mobileorganizer.utils.ADD_NOTE_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.NOTES_LIST_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.OnRecyclerViewEventsListener
import com.iit.secondcourse.mobileorganizer.utils.SwipeToDeleteCallback


class NotesListFragment : Fragment() {

    //view binding
    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NotesRecyclerViewAdapter

    //view model
    private val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(((activity as MainActivity).application as MobileOrganizerApplication).repository)
    }

    //card listener
    private val itemClickListener = object : OnRecyclerViewEventsListener {
        override fun onItemClick(id: Long) {
            Log.d(NOTES_LIST_FRAGMENT, "OnCardClick")
            goToNoteViewService?.onNoteClick(id)
        }

        override fun onItemSwiped(note: Note) {
            showDeleteDialog(note)
        }
    }

    //open note view fragment click listener
    private var goToNoteViewService: OnGoToNoteViewService? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerViewAdapter()

        setListeners()

        setNotesObservers()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnGoToNoteViewService) {
            goToNoteViewService = context
        }
    }

    override fun onDetach() {
        goToNoteViewService = null
        super.onDetach()
    }

    private fun showDeleteDialog(note: Note) {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.apply {
            setIcon(R.drawable.ic_round_delete_24)
            setTitle("Warning")
            setMessage("Are you sure in deleting the note?")
            setPositiveButton("Delete") { _, _ ->
                noteViewModel.deleteNote(note)
            }
            setNegativeButton("Cancel") { _, _ ->
                noteViewModel.getNotes()?.let { adapter.submitList(it) }
            }
            setOnCancelListener {
                noteViewModel.getNotes()?.let { adapter.submitList(it) }
            }
        }.create().show()
    }

    private fun setRecyclerViewAdapter() {
        adapter = NotesRecyclerViewAdapter(itemClickListener)
        binding.fnlRvNotesList.adapter = adapter
        noteViewModel.getNotes()?.let { adapter.submitList(it) }
        val callback: ItemTouchHelper.Callback = SwipeToDeleteCallback(adapter)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.fnlRvNotesList)
    }

    private fun setListeners() {
        binding.fnlBtnAddNote.setOnClickListener {
            Log.d(NOTES_LIST_FRAGMENT, "btnAddNote clicked")
            val noteFragment = NoteAddDialogFragment.newInstance()
            noteFragment.show((activity as MainActivity).supportFragmentManager, ADD_NOTE_FRAGMENT)
        }
    }

    private fun setNotesObservers() {
        noteViewModel.allNotes.observe(viewLifecycleOwner, { notes ->
            // Update the cached copy of the words in the adapter.
            notes?.let { adapter.submitList(it) }
        })
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = NotesListFragment()
    }
}