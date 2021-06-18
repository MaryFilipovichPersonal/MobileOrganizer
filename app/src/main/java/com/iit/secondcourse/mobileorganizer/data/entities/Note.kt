package com.iit.secondcourse.mobileorganizer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iit.secondcourse.mobileorganizer.utils.*
import java.util.*

@Entity(tableName = NOTE_TABLE_NAME)
data class Note(
    @ColumnInfo(name = NOTE_TITLE) val title: String,
    @ColumnInfo(name = NOTE_CONTENT) val content: String,
    @ColumnInfo(name = NOTE_DATE_CREATE) val dateCreate: Calendar,
    @ColumnInfo(name = NOTE_DATE_UPDATE) val dateUpdate: Calendar,
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
