package com.example.demoexample

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {

    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String, @Query("apiKey") apiKey: String): Call<TopHeadlinesModel>

}