package com.iit.secondcourse.mobileorganizer.ui.view.noteslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iit.secondcourse.mobileorganizer.databinding.FragmentNotesListBinding

class NotesListFragment: Fragment() {

    //view binding
    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

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
        //TODO
    }

    override fun onDestroyView() {
       _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val TAG = "NotesListFragment"
        fun newInstance() = NotesListFragment()
    }
}