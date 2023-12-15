package com.kotlinpratice.roomwordexample

import android.app.Application
import com.kotlinpratice.roomwordexample.repository.WordRepository
import com.kotlinpratice.roomwordexample.room.roomdatabase.WordRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class WordsApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy {
        WordRoomDatabase.getDatabase(this, applicationScope)
    }

    val repository by lazy {
        WordRepository(database.wordDao())
    }

}