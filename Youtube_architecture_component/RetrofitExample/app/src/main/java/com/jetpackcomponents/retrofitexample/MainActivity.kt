package com.jetpackcomponents.retrofitexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//Retrofit With Coroutines
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quotesAPI = RetrofitHelper.getInstance().create(QuotesApi::class.java)

        //coroutines
        GlobalScope.launch {
            //API CALL and Long Running Task - background thread
            val result = quotesAPI.getQuotes(1)
            if (result != null){
                val quoteList = result.body()
                if (quoteList != null) {
                    quoteList.results.forEach {
                        Log.d("Akash", it.content)
                    }
                }
            }
        }


    }
}