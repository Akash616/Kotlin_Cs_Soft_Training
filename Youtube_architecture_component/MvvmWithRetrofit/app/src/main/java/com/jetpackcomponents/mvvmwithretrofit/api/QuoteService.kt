package com.jetpackcomponents.mvvmwithretrofit.api

import com.jetpackcomponents.mvvmwithretrofit.models.AuthorsList
import com.jetpackcomponents.mvvmwithretrofit.models.QuoteList
import com.jetpackcomponents.mvvmwithretrofit.models.QuotesTags
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteService {

    @GET("/quotes")
    suspend fun getQuotes(@Query("page") page: Int) : Response<QuoteList>
    //https://api.quotable.io/quotes?page=1

    @GET("/authors")
    suspend fun getAuthors(@Query("sortBy") sortBy: String, @Query("order") order: String) : Response<AuthorsList>
    //https://api.quotable.io/authors?sortBy=name&order=asc

    @GET("/quotes")
    suspend fun getQuotesTag(@Query("tags") tags: String) : Response<QuotesTags>
    //https://api.quotable.io/quotes?tags=love|happiness

}