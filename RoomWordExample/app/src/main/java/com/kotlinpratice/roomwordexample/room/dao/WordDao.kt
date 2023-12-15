package com.kotlinpratice.roomwordexample.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.kotlinpratice.roomwordexample.room.entity.Word
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {

    //@Dao annotation identifies it as a DAO class for Room.
    //Room uses the DAO to create a clean API for your code.
    //In the DAO (data access object), you specify SQL queries and associate them with method calls.

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    fun getAlphabetizedWords() : Flow<List<Word>>
    //To observe data changes you will use Flow from kotlinx-coroutines.
    // the queries are automatically run asynchronously on a background thread.

    //The selected onConflict strategy ignores a new word if it's exactly the same as one already in the list.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWord(word: Word)

    @Update
    suspend fun updateWord(word: Word)

    @Delete
    suspend fun deleteWord(word: Word)

    @Query("DELETE FROM word_table")
    suspend fun deleteAllWords()

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    fun loadAllByIds(userIds: IntArray): List<User>
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    fun findByName(first: String, last: String): User

}