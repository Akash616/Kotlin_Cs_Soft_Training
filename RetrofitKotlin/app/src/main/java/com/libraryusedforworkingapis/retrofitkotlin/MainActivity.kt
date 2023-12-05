package com.libraryusedforworkingapis.retrofitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.libraryusedforworkingapis.retrofitkotlin.api.ApiInterface
import com.libraryusedforworkingapis.retrofitkotlin.api.ApiUtilities
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usersApi = ApiUtilities.getInstance().create(ApiInterface::class.java)

        //val result = usersApi.getUsers()
        //error bec getUsers() is suspend fun, so call from coroutine

        //Coroutines
        //using a coroutine builder like launch to start a new coroutine
//        GlobalScope.launch {
//            val result = usersApi.getUsers()
//            if (result.body() != null){ //data is there
//                //Log.d("Result", "onCreate: ${result.body()}")
//                result.body()!!.iterator().forEach {
//                    Log.d("Result", "name: ${it.login}")
//                }
//            }
//        }

        //To specify where the coroutines should run, Kotlin provides three dispatchers that you can use:
        //Dispatchers.Main, Dispatchers.IO, Dispatchers.Default
        //Coroutine Scope defines a scope for coroutines.
        // Create a new coroutine to move the execution off the UI thread
        CoroutineScope(Dispatchers.IO).launch {
            val response = usersApi.getUsers()
            if (response.body() != null){
                Log.d("Response : ", "${response.body()}")
            }
        }

    }
}