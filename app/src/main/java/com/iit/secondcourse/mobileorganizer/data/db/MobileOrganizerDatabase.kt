package com.iit.secondcourse.mobileorganizer.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.iit.secondcourse.mobileorganizer.data.db.dao.NoteDao
import com.iit.secondcourse.mobileorganizer.data.db.dao.SubtaskDao
import com.iit.secondcourse.mobileorganizer.data.db.dao.TaskDao
import com.iit.secondcourse.mobileorganizer.data.db.dao.TaskSubtaskDao
import com.iit.secondcourse.mobileorganizer.data.db.utils.Converters
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.data.entities.Task
import com.iit.secondcourse.mobileorganizer.utils.DATABASE_NAME
import com.iit.secondcourse.mobileorganizer.utils.TestDataProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(
    entities = arrayOf(Note::class, Task::class, Subtask::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
public abstract class MobileOrganizerDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun taskDao(): TaskDao
    abstract fun subtaskDao(): SubtaskDao
    abstract fun taskSubtaskDao(): TaskSubtaskDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MobileOrganizerDatabase? = null

        private class MobileDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        setTestNotes(database.noteDao())
                        setTestTasks(database.taskSubtaskDao())
                    }
                }
            }

            suspend fun setTestNotes(noteDao: NoteDao) {
                noteDao.deleteAllNotes()
                noteDao.insertNotesList(TestDataProvider.getNotes())
            }
            suspend fun setTestTasks(taskSubtaskDao: TaskSubtaskDao) {
                taskSubtaskDao.deleteAllTasksSubtasks()
                TestDataProvider.getTasks().forEach {
                    taskSubtaskDao.insertTask(it)
                }

            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope): MobileOrganizerDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MobileOrganizerDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(MobileDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                //return instance
                instance
            }
        }

    }

}