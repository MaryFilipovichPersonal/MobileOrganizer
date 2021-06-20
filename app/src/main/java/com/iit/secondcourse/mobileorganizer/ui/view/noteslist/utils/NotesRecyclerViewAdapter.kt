package com.iit.secondcourse.mobileorganizer.ui.view.noteslist.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.iit.secondcourse.mobileorganizer.R
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.utils.DateUtils
import com.iit.secondcourse.mobileorganizer.utils.SwipeToDeleteCallback

class NotesRecyclerViewAdapter(private val listenerNote: OnNoteRecyclerViewEventsListener) :
    RecyclerView.Adapter<NoteViewHolder>(), SwipeToDeleteCallback.ItemTouchHelperAdapter {

    private var notes: List<Note> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_note, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bind(note)
        holder.itemView.setOnClickListener {
            listenerNote.onNoteItemClick(note.id)
        }
    }

    override fun getItemCount() = notes.size

    fun submitList(newList: List<Note>) {
        if (notes != newList) {
            val diffUtilsCallback = NotesDiffUtilsCallback(notes, newList)
            val diffResult = DiffUtil.calculateDiff(diffUtilsCallback)
            diffResult.dispatchUpdatesTo(this)
            notes = newList
        } else {
            notes = newList
            notifyDataSetChanged()
        }

    }

    override fun onRowSwiped(position: Int) {
        listenerNote.onNoteItemSwiped(notes[position])
    }

}

class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var tvNoteTitle: TextView = itemView.findViewById(R.id.vhn_tv_note_title)
    private var tvNoteContent: TextView = itemView.findViewById(R.id.vhn_tv_note_content)
    private var tvNoteDateCreate: TextView = itemView.findViewById(R.id.vhn_tv_note_create)
    private var tvNoteDateUpdate: TextView = itemView.findViewById(R.id.vhn_tv_note_update)
    private var tvCategory: TextView = itemView.findViewById(R.id.vhn_tv_note_category)

    fun bind(note: Note) {
        tvNoteTitle.text = context.getString(R.string.title, note.title)
        tvNoteContent.text = context.getString(R.string.content, note.content)
        //TODO: add categories support
//            tvCategory.text = String.format(R.string.category.toString(), note.category)
        tvNoteDateCreate.text =
            context.getString(R.string.date_create, DateUtils.getNoteFormattedDate(note.dateCreate))
        tvNoteDateUpdate.text =
            context.getString(R.string.date_update, DateUtils.getNoteFormattedDate(note.dateUpdate))
    }
}

private val RecyclerView.ViewHolder.context
    get() = this.itemView.context
