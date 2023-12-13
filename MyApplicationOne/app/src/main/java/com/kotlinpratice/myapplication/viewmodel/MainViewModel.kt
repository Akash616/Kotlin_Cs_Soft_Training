package com.kotlinpratice.myapplication.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlinpratice.myapplication.api.QuoteService
import com.kotlinpratice.myapplication.model.QuotesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteService: QuoteService) : ViewModel() {

    private val _quoteLiveData = MutableLiveData<QuotesResponse>()
    val quoteLiveData: LiveData<QuotesResponse>
        get() = _quoteLiveData

    suspend fun getQuotes(page: Int) {
        val response = quoteService.getQuotes(page)
        if (response != null && response.body() != null) {
            _quoteLiveData.postValue(response.body())
        } else {
            Log.d("onFailure: ", ""+response.errorBody())
        }
    }

}