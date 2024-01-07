package com.example.demoexample

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository(private val apiClient: APIClient) {

    private val _topHeadlineLiveData = MutableLiveData<TopHeadlinesModel>()
    val topHeadlineLiveData: LiveData<TopHeadlinesModel>
        get() = _topHeadlineLiveData

    suspend fun getTopHeadlines(country: String, apiKey: String){
        //apiClient.getTopHeadlines(country, apiKey)
        val call: Call<TopHeadlinesModel> = apiClient.getTopHeadlines(country, apiKey)
        call.enqueue(object : Callback<TopHeadlinesModel>{
            override fun onResponse(
                call: Call<TopHeadlinesModel>,
                response: Response<TopHeadlinesModel>
            ) {
                if (response.isSuccessful){
                    _topHeadlineLiveData.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<TopHeadlinesModel>, t: Throwable) {
                Log.d("onFailure:", t.message.toString())
            }

        })
    }

}