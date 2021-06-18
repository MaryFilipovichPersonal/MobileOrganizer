package com.iit.secondcourse.mobileorganizer.ui.view.noteslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.iit.secondcourse.mobileorganizer.MobileOrganizerApplication
import com.iit.secondcourse.mobileorganizer.databinding.FragmentNotesListBinding
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModel
import com.iit.secondcourse.mobileorganizer.ui.presenter.note.NoteViewModelFactory
import com.iit.secondcourse.mobileorganizer.ui.view.MainActivity
import com.iit.secondcourse.mobileorganizer.ui.view.noteslist.utils.NotesRecyclerViewAdapter
import com.iit.secondcourse.mobileorganizer.utils.NOTES_LIST_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.OnItemClickListener

class NotesListFragment: Fragment() {

    //view binding
    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: NotesRecyclerViewAdapter

    //view model
    private  val noteViewModel: NoteViewModel by viewModels {
        NoteViewModelFactory(((activity as MainActivity).application as MobileOrganizerApplication).repository)
    }

    //card listener
    private val listener = object : OnItemClickListener {
        override fun onItemClick(id: Long) {
            Log.d(NOTES_LIST_FRAGMENT, "OnCardClick")
        }
    }

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

        setNotesObservers()
    }

    private fun setRecyclerViewAdapter() {
        adapter = NotesRecyclerViewAdapter(listener)
        binding.fnlRvNotesList.adapter = adapter
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