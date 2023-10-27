package com.libraryusedforworkingapis.retrofitkotlin.api

import com.libraryusedforworkingapis.retrofitkotlin.model.Users
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("users")
    suspend fun getUsers() : Response<Users>
    //suspend - run in background thread.

}