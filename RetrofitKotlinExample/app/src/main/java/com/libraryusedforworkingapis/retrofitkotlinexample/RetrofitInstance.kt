package com.libraryusedforworkingapis.retrofitkotlinexample

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance { //Retrofit Object for API call

    private val retrofit by lazy {
        //lazy means retrofit variable ko lazy brackets{ka inside Initialize karna wala hu}
        Retrofit.Builder().baseUrl("https://meme-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInterface by lazy {
        retrofit.create(ApiInterface::class.java)
    }

}