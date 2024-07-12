package com.example.memesapp.Api

data class Meme(
    val data: Data,
    val success: Boolean
)

data class Data(
    val memes: List<MemeX>
)
data class MemeX(
    val box_count: Int,
    val captions: Int,
    val height: Int,
    val id: String,
    val name: String,
    val url: String,
    val width: Int
)