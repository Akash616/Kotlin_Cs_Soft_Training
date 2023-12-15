package com.kotlinpratice.keepnotes.repository

import androidx.lifecycle.LiveData
import com.kotlinpratice.keepnotes.room.dao.NoteDao
import com.kotlinpratice.keepnotes.room.entity.Note

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NoteRepository(private val noteDao: NoteDao) { //Repository - access to multiple data sources.

    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes() //function

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){
        noteDao.delete(note)
    }

}