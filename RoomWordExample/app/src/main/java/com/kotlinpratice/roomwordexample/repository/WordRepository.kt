package com.kotlinpratice.roomwordexample.repository

import androidx.annotation.WorkerThread
import com.kotlinpratice.roomwordexample.room.dao.WordDao
import com.kotlinpratice.roomwordexample.room.entity.Word
import kotlinx.coroutines.flow.Flow

class WordRepository(private val wordDao: WordDao) {
    //A repository class abstracts access to multiple data sources.

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @WorkerThread
    suspend fun insertWord(word: Word){
        wordDao.insertWord(word)
    }

    @WorkerThread
    suspend fun updateWord(word: Word){
        wordDao.updateWord(word)
    }

    @WorkerThread
    suspend fun deleteWord(word: Word){
        wordDao.deleteWord(word)
    }

    @WorkerThread
    suspend fun deleteAllWords(){
        wordDao.deleteAllWords()
    }

}