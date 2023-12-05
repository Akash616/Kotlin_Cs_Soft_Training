package com.kotlinpratice.myapplication.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlinpratice.myapplication.api.QuoteService
import com.kotlinpratice.myapplication.viewmodel.MainViewModel

class MainViewModelFactory(private val quoteService: QuoteService) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(quoteService) as T
    }

}