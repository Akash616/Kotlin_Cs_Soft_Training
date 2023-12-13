package com.danniyal.weatherappkotlinclassic

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.openweathermap.org/data/2.5/";


class MainActivity : AppCompatActivity() {

    //https://api.openweathermap.org/data/2.5/forecast?q=dehradun&appid=5b326caeab0bcc69b9bae6f3e800c16a&units=metric

    var tv_temp: TextView? = null
    var weatherName: TextView? = null
    var ivWeatherIcon: ImageView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_temp = findViewById(R.id.tv_temp)
        weatherName = findViewById(R.id.weatherName)
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon)

        fetchWeatherData()
    }


    private fun fetchWeatherData() {

        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val response =
            retrofit.getWeatherData("dehradun", "5b326caeab0bcc69b9bae6f3e800c16a", "metric")


        response.enqueue(object : Callback<Example> {
            override fun onResponse(call: Call<Example>, response: Response<Example>) {

                val responseBody = response.body()!!

                tv_temp!!.text = responseBody.list[0].main.temp.toString()
                weatherName!!.text = responseBody.list[0].weather[0].main+" "+
                        responseBody.list[0].main.temp_max+"/"+responseBody.list[0].main.temp_min

               if (responseBody.list[0].weather[0].icon.equals("01d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a01d)
               } else if (responseBody.list[0].weather[0].icon.equals("01n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a01n)
               } else if (responseBody.list[0].weather[0].icon.equals("02d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a02d)
               }else if (responseBody.list[0].weather[0].icon.equals("02n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a02n)
               }else if (responseBody.list[0].weather[0].icon.equals("03d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a03d)
               }else if (responseBody.list[0].weather[0].icon.equals("03n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a03n)
               }else if (responseBody.list[0].weather[0].icon.equals("04d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a04d)
               }else if (responseBody.list[0].weather[0].icon.equals("04n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a04n)
               }else if (responseBody.list[0].weather[0].icon.equals("09d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a09d)
               }else if (responseBody.list[0].weather[0].icon.equals("09n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a09n)
               }else if (responseBody.list[0].weather[0].icon.equals("10d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a10d)
               }else if (responseBody.list[0].weather[0].icon.equals("10n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a10n)
               }else if (responseBody.list[0].weather[0].icon.equals("11d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a11d)
               }else if (responseBody.list[0].weather[0].icon.equals("11n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a11n)
               }else if (responseBody.list[0].weather[0].icon.equals("13d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a13d)
               }else if (responseBody.list[0].weather[0].icon.equals("13n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a13n)
               }else if (responseBody.list[0].weather[0].icon.equals("50d")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a50d)
               }else if (responseBody.list[0].weather[0].icon.equals("50n")){
                   ivWeatherIcon!!.setImageResource(R.drawable.a50n)
               }

            }

            override fun onFailure(call: Call<Example>, t: Throwable) {
                Log.d("DATA", t.toString())
            }

        })


    }
}