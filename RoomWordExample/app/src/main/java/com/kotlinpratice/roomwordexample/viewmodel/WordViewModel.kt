package com.kotlinpratice.roomwordexample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.kotlinpratice.roomwordexample.repository.WordRepository
import com.kotlinpratice.roomwordexample.room.entity.Word
import kotlinx.coroutines.launch

class WordViewModel(private val wordRepository: WordRepository): ViewModel() {

    //converted the Flow to LiveData by calling asLiveData().
    val allWords: LiveData<List<Word>> = wordRepository.allWords.asLiveData()

    //Launching a new coroutine to insert the data in a non-blocking way
    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insertWord(word)
    }

    fun update(word: Word) = viewModelScope.launch {
        wordRepository.updateWord(word)
    }

    fun delete(word: Word) = viewModelScope.launch {
        wordRepository.deleteWord(word)
    }

    fun deleteAll() = viewModelScope.launch {
        wordRepository.deleteAllWords()
    }

}