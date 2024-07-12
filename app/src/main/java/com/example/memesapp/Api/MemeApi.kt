package com.example.memesapp.Api

import retrofit2.http.GET

interface MemeApi {
    @GET("get_memes")
    suspend fun getMeme():Meme
}