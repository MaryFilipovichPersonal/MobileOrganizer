package com.iit.secondcourse.mobileorganizer.utils

const val MAIN_ACTIVITY = "MainActivity"
const val MAIN_FRAGMENT = "MainFragment"
const val NOTES_LIST_FRAGMENT = "NotesListFragment"
const val TASKS_LIST_FRAGMENT = "TasksListFragment"
const val ADD_NOTE_FRAGMENT = "NoteAddDialogFragment"
const val VIEW_NOTE_FRAGMENT = "ViewNoteFragment"

// database -------------------
const val DATABASE_NAME = "mobile_organizer_database"
    // Note table .......................
const val NOTE_TABLE_NAME = "note_table"
const val NOTE_TITLE = "title"
const val NOTE_CONTENT = "content"
const val NOTE_DATE_CREATE = "date_create"
const val NOTE_DATE_UPDATE = "date_update"
    //..................................
    // Task table ......................
const val TASK_TABLE_NAME = "task_table"
const val TASK_TITLE = "title"
const val TASK_DESCRIPTION = "description"
const val TASK_DATE_START = "date_start"
const val TASK_DATE_DEADLINE = "date_deadline"
const val TASK_COMPLETED = "completed"
    //..................................
    // Subtask table ...................
const val SUBTASK_TABLE_NAME = "subtask_table"
const val SUBTASK_DATE_START = "task_id"
const val SUBTASK_TITLE = "title"
const val SUBTASK_DESCRIPTION = "description"
const val SUBTASK_DATE_DEADLINE = "date_deadline"
const val SUBTASK_COMPLETED = "completed"
    //..................................
const val CATEGORY_TABLE_NAME = "category_table"
const val CATEGORY_ID = "category_id"
const val CATEGORY_TITLE = "category_title"
//-------------------------------

//Bundle
const val NOTE_ID = "note_id"
//-------------------------------

val TAB_FRAGMENTS_TITLES = arrayListOf("Заметки", "Задачи")

