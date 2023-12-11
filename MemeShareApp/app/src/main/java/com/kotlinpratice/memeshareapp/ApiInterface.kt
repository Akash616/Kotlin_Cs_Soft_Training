package com.kotlinpratice.memeshareapp

import com.kotlinpratice.memeshareapp.modal.RandomMeme
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {

    @GET("/gimme")
    fun getRandomMeme() : Call<RandomMeme>

}