package com.example.memesapp.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val api: MemeApi by lazy{
        Retrofit.Builder()
            .baseUrl("https://api.imgflip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MemeApi::class.java)
    }
}