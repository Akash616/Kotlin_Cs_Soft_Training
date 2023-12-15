package com.kotlinpratice.keepnotes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlinpratice.keepnotes.R
import com.kotlinpratice.keepnotes.room.entity.Note

class NoteRvAdapter(private val listener: INotesRvAdapter): RecyclerView.Adapter<NoteRvAdapter.NoteViewHolder>() {

    val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvNote = itemView.findViewById<TextView>(R.id.tvNote)
        val ivDeleteIcon = itemView.findViewById<ImageView>(R.id.ivDeleteIcon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_items, parent, false)
        val noteViewHolder = NoteViewHolder(view)
        noteViewHolder.ivDeleteIcon.setOnClickListener {
            listener.onItemClicked(allNotes[noteViewHolder.adapterPosition])
        }
        return noteViewHolder
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.tvNote.text = currentNote.text
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface INotesRvAdapter{
    fun onItemClicked(note: Note)
}