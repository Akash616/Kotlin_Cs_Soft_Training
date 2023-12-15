package com.kotlinpratice.roomwordexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kotlinpratice.roomwordexample.R
import com.kotlinpratice.roomwordexample.room.entity.Word

class WordListAdapter : ListAdapter<Word, WordListAdapter.WordViewHolder>(WordsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.words)
    }

    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val tvWord: TextView = itemView.findViewById(R.id.tvWord)

        fun bind(text: String?){
            tvWord.text = text
        }

        companion object{
            fun create(parent: ViewGroup): WordViewHolder{
                val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
                val viewHolder = WordViewHolder(view)
                return viewHolder
            }
        }
    }

    class WordsComparator: DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.words == newItem.words
        }

    }

}