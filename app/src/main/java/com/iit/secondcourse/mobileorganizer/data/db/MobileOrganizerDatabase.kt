package com.iit.secondcourse.mobileorganizer.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.iit.secondcourse.mobileorganizer.data.db.dao.NoteDao
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.utils.DATABASE_NAME

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
public abstract class MobileOrganizerDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: MobileOrganizerDatabase? = null

        fun getDatabase(context: Context): MobileOrganizerDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MobileOrganizerDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                //return instance
                instance
            }
        }

    }

}