package com.jetpackcomponents.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    //ViewModel Only Holds Data
    var count: Int = 0

    fun increment() {
        count++
    }

}