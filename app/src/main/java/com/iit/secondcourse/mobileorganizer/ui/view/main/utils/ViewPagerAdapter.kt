package com.iit.secondcourse.mobileorganizer.ui.view.main.utils

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iit.secondcourse.mobileorganizer.ui.view.noteslist.NotesListFragment
import com.iit.secondcourse.mobileorganizer.ui.view.taskslist.TasksListFragment
import com.iit.secondcourse.mobileorganizer.utils.TAB_FRAGMENTS_TITLES

class ViewPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter (manager, lifecycle) {

    override fun getItemCount() = TAB_FRAGMENTS_TITLES.size

    override fun createFragment(position: Int) = when(position) {
        0 -> NotesListFragment.newInstance()
        1 -> TasksListFragment.newInstance()
        else -> NotesListFragment.newInstance()
    }

}