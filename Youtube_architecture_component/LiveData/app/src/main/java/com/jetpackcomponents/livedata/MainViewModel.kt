package com.jetpackcomponents.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    //Observable Data Holder Class
    /*Live data 2 types:
    * MutableLiveData - data change kar sakta hai
    * LiveData - data change nahi kar sakta*/
    private val factsLiveObject = MutableLiveData<String>("This is a fact")

    val factsLiveData : LiveData<String>
        get() = factsLiveObject

    fun updateLiveData(){
        factsLiveObject.value = "Another Fact"
    }

}