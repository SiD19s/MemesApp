package com.example.memesapp.download

interface Downloader {
    fun downloadFile(url: String,name: String): Long
}