package com.jetpackcomponents.viewmodelfactory

import androidx.lifecycle.ViewModel

class MainViewModel(val iniitalvalue : Int): ViewModel() {

    //ViewModel Only Holds Data
    var count: Int = iniitalvalue

    fun increment() {
        count++
    }

}