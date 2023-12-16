package com.kotlinpratice.keepnotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlinpratice.keepnotes.adapter.INotesRvAdapter
import com.kotlinpratice.keepnotes.adapter.NoteRvAdapter
import com.kotlinpratice.keepnotes.room.entity.Note
import com.kotlinpratice.keepnotes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity(), INotesRvAdapter {

    lateinit var viewModel: NoteViewModel
    lateinit var rvNotes: RecyclerView
    lateinit var etInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvNotes = findViewById(R.id.rvNotes)
        etInput = findViewById(R.id.etInput)

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)

        rvNotes.layoutManager = LinearLayoutManager(this)
        val adapter = NoteRvAdapter(this)
        rvNotes.adapter = adapter

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {//let - execute a code block containing non-null values.
                adapter.updateList(it)
            }
        })

    }

    override fun onItemClicked(note: Note) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "${note.text} Deleted", Toast.LENGTH_SHORT).show()
    }

    fun createSubmit(view: View) {
        val noteText = etInput.text.toString()
        if (noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "$noteText Inserted", Toast.LENGTH_SHORT).show()
        }
    }

}