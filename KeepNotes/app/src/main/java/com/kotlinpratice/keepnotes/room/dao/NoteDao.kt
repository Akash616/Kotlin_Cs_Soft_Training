package com.kotlinpratice.keepnotes.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlinpratice.keepnotes.room.entity.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)
    //The suspend keyword means that this function can be blocking.
    //It's IO OPERATION running on main thread(UI thread), you app will slow
    //So, abstract function running on background thread using coroutines (suspend(async) <-> coroutines(await))
    //means long running task will run on background, your app will not slow.
    //suspend fun.? -> ya walaa function sirf background thread say hi call ho sakta hai OR
    // ya phir ak dusra suspend function say call ho sakta hai.
    //means normal fun. say call nahi ho sakta.

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notes_table order by id ASC")
    fun getAllNotes(): LiveData<List<Note>>
    //Jasa hi is table(Note) ka andar agar kuch change hora hai, mujha(Activity/Fragment) ko pata
    //chalna chahiya. SO, we use LiveData<> -> Life Cycle Aware, it's like wrapper on data.
    //hum Activity ko Observer bana denga.
    //Room support LiveData.

}