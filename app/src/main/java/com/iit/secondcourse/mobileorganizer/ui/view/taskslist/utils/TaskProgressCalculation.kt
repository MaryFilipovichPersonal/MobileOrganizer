package com.iit.secondcourse.mobileorganizer.ui.view.taskslist.utils

object TaskProgressCalculation {
    fun getProgressFromCompletedSubtasksNum(size: Int, completedNum: Int): Float =
        ((completedNum * 100) / size).toFloat()
}