package com.kotlinpratice.keepnotes.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kotlinpratice.keepnotes.room.dao.NoteDao
import com.kotlinpratice.keepnotes.room.entity.Note

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() { //abstract - we can't create an object of this class. But we create object of children class.
    //Database is a Entry point.

    abstract fun getNoteDao(): NoteDao //The Room database uses the DAO to issue queries to the SQLite database.

    companion object{
        // Singleton prevents multiple instances of database opening at the
        // same time. (Singleton class means only one instance)
        //synchronized(this) block -> Thread safe(means koi or thread isko is time pay access na kara jab tak ya chalra hai).
        @Volatile //in order to force changes in a variable to be immediately visible to other threads
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }

}