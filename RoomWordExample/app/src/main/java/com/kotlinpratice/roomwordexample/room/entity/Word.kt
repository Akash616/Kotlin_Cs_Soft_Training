package com.kotlinpratice.roomwordexample.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Room allows you to create tables via an Entity.
@Entity(tableName = "word_table") //annotations
data class Word(

    //@PrimaryKey(autoGenerate = true) val id: Int,
    @PrimaryKey @ColumnInfo("word") val words: String

)
