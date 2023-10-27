package com.libraryusedforworkingapis.retrofitkotlinexample

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface { //API service
    //Interface ki help say, app ko kaunsa END POINT pai hit karna hai.
    //BASE URL(https://meme-api.com/) END POINT(gimme)

    @GET("gimme")
    fun getData(): Call<ResponseDataClass>

}