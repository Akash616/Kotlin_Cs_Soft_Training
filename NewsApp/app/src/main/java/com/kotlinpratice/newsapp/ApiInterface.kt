package com.kotlinpratice.newsapp

import com.kotlinpratice.newsapp.modal.NewsModal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("/top-headlines")
    fun getTopHeadlines(@Query("country") country: String, @Query("apiKey") apiKey: String) : Call<NewsModal>

}