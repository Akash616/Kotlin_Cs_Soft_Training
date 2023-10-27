package com.jetpackcomponents.mvvmwithretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jetpackcomponents.mvvmwithretrofit.api.QuoteService
import com.jetpackcomponents.mvvmwithretrofit.models.AuthorsList
import com.jetpackcomponents.mvvmwithretrofit.models.QuoteList
import com.jetpackcomponents.mvvmwithretrofit.models.QuotesTags

//Repository - use to manage the data
class QuoteRepository(private val quoteService : QuoteService) { //API reference only use

    private val quotesLiveData = MutableLiveData<QuoteList>()
    val quotes : LiveData<QuoteList>
        get() = quotesLiveData

    private val authorsLiveData = MutableLiveData<AuthorsList>()
    val authors : LiveData<AuthorsList>
        get() = authorsLiveData

    private val quotesTagsLiveData = MutableLiveData<QuotesTags>()
    val tags : LiveData<QuotesTags>
        get() = quotesTagsLiveData

    suspend fun getQuotes(page : Int){
        val result = quoteService.getQuotes(page)
        if(result != null && result.body() != null){
            quotesLiveData.postValue(result.body())
            //quotesLiveData.value = result.body()
        }
    }

    suspend fun getAuthors(sortBy: String, order: String){
        val result = quoteService.getAuthors(sortBy, order)
        if (result != null && result.body() != null){
            authorsLiveData.postValue(result.body())
        }
    }

    suspend fun getQuotesTag(tags: String){
        val result =  quoteService.getQuotesTag(tags)
        if (result != null && result.body() != null){
            quotesTagsLiveData.postValue(result.body())
        }
    }

}