package com.danniyal.weatherappkotlinclassic

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("forecast")
    fun getWeatherData(@Query("q") city: String,
                       @Query("appid") appid: String, @Query("units") units: String): Call<Example>
}