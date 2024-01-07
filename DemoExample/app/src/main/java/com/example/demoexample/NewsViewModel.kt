package com.example.demoexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(private val newsRepository: NewsRepository, private val country: String, private val apiKey: String): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            newsRepository.getTopHeadlines(country, apiKey)
        }
    }

    val topHeadline: LiveData<TopHeadlinesModel>
        get() = newsRepository.topHeadlineLiveData

}