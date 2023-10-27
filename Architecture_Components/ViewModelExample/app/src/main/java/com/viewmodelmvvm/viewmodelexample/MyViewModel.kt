package com.viewmodelmvvm.viewmodelexample

import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {

    //Now, we will create instruction that needs to be performed on the data.
    //But, where is data I mean model.
    //see as this is a very simple project so there is no such big database
    //like SQL or room database. Here we have our data
    private var counter = 0
    //this counter variable is a data or model.

    //View Model send and receive the request such as create,read,update or delete data.
    //Here we will perform 2 requests
    //first is to read the data and
    //second is to update the data
    fun getInitialCounter(): Int{
        return counter
    }
    fun getUpdatedCounter(): Int{
        return ++counter
    }


}