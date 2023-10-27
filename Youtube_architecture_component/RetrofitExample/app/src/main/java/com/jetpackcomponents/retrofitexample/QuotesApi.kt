package com.jetpackcomponents.retrofitexample

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuotesApi {

    //https://api.quotable.io/quotes?page=1
    @GET("/quotes")
    //coroutines
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>

    //Is tarika say hum diff. function banata hai, diff. End Point ko Hit karna ka leya.

}