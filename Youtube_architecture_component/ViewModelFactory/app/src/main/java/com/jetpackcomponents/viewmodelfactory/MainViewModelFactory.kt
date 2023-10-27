package com.jetpackcomponents.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory(val counter: Int) : ViewModelProvider.Factory {

    //Ab hum is factory ka use karanga ViewModel ko banana ka leya
    //jo bhi data hoga, mai is factory ko pass karunga
    //factory uska according huma ViewModel bana ka dagi.

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(counter) as T //cast - bec. generic used
    }

}