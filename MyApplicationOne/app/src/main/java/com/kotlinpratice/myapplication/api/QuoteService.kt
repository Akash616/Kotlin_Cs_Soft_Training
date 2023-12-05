package com.kotlinpratice.myapplication.api

import com.kotlinpratice.myapplication.model.QuotesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuotesResponse>
    //https://api.quotable.io/quotes?page=1

}