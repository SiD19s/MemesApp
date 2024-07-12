package com.example.memesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memesapp.Api.Meme
import com.example.memesapp.Api.RetrofitInstance
import kotlinx.coroutines.launch

class MemeViewModel : ViewModel() {
    private val _meme = MutableLiveData<Meme>()
    val meme: LiveData<Meme> = _meme

    init {
        fetchMeme()
    }
    var count = 0
    fun fetchMeme() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getMeme()
            val data = response.data

            val success = response.success
            _meme.value = Meme(data, success)
        }
    }
}