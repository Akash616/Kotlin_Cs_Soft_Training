package com.jetpackcomponents.mvvmwithretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetpackcomponents.mvvmwithretrofit.models.AuthorsList
import com.jetpackcomponents.mvvmwithretrofit.models.QuoteList
import com.jetpackcomponents.mvvmwithretrofit.models.QuotesTags
import com.jetpackcomponents.mvvmwithretrofit.repository.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: QuoteRepository) : ViewModel() {


    init { //The code inside the init block is the first to be executed when the class is instantiated.
        viewModelScope.launch(Dispatchers.IO) {//Coroutine
            //The CoroutineDispatcher that is designed for offloading blocking IO tasks to a shared pool of threads.
            repository.getQuotes(1)
            repository.getAuthors("name", "asc")
            repository.getQuotesTag("love|happiness")
        }
    }

    val quotes : LiveData<QuoteList>
        get() = repository.quotes

    val authors : LiveData<AuthorsList>
        get() = repository.authors

    val quotesTags : LiveData<QuotesTags>
        get() = repository.tags

}