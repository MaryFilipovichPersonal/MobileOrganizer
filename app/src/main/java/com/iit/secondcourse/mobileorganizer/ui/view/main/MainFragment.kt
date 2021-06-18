package com.iit.secondcourse.mobileorganizer.ui.view.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.iit.secondcourse.mobileorganizer.databinding.FragmentMainBinding
import com.iit.secondcourse.mobileorganizer.ui.view.addnote.NoteAddDialogFragment
import com.iit.secondcourse.mobileorganizer.ui.view.main.utils.ViewPagerAdapter
import com.iit.secondcourse.mobileorganizer.ui.view.noteslist.utils.OnAddNotesBtnClickListener
import com.iit.secondcourse.mobileorganizer.utils.ADD_NOTE_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.MAIN_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.TAB_FRAGMENTS_TITLES

class MainFragment : Fragment(), OnAddNotesBtnClickListener {

    //view binding
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initTabs()
    }

    private fun initTabs() {

        binding.fmViewPager.adapter = ViewPagerAdapter(childFragmentManager, lifecycle)

        TabLayoutMediator(
            binding.mfTabLayout,
            binding.fmViewPager
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = TAB_FRAGMENTS_TITLES[position]
        }.attach()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onAddNoteBtnClick() {
        Log.d(MAIN_FRAGMENT, "onAddNoteBtnClick")

    }
}