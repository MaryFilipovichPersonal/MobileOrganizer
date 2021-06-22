package com.iit.secondcourse.mobileorganizer.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.iit.secondcourse.mobileorganizer.databinding.ActivityMainBinding
import com.iit.secondcourse.mobileorganizer.ui.view.addtask.TaskAddFragment
import com.iit.secondcourse.mobileorganizer.ui.view.main.MainFragment
import com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils.OnTasksListFragmentEventListener
import com.iit.secondcourse.mobileorganizer.ui.view.viewnote.ViewNoteFragment
import com.iit.secondcourse.mobileorganizer.ui.view.viewnote.utils.OnGoToNoteViewService
import com.iit.secondcourse.mobileorganizer.utils.ADD_TASK_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.MAIN_FRAGMENT
import com.iit.secondcourse.mobileorganizer.utils.VIEW_NOTE_FRAGMENT

class MainActivity : AppCompatActivity(), OnGoToNoteViewService, OnTasksListFragmentEventListener {

    //view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, MainFragment.newInstance(), MAIN_FRAGMENT)
            .commit()
    }

    private fun openViewNoteFragment(id: Long) {
        val fragment = ViewNoteFragment.newInstance(id)
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment, VIEW_NOTE_FRAGMENT)
            .addToBackStack(null)
            .commit()
    }

    private fun openTaskAddFragment() {
        val fragment = TaskAddFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, fragment, ADD_TASK_FRAGMENT)
            .addToBackStack(null)
            .commit()
    }

    override fun onNoteClick(id: Long) {
        openViewNoteFragment(id)
    }

    override fun onAddTaskBtnClick() {
        openTaskAddFragment()
    }

}