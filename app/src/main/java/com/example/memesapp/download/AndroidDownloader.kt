package com.example.memesapp.download

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri

class AndroidDownloader(
    private val context : Context
):Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)
    override fun downloadFile(url: String,name:String): Long {
        val request = DownloadManager.Request(url.toUri())
            .setMimeType("image/jpg")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
            .setTitle("$name.jpg")
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"image.jpg")
        return downloadManager.enqueue(request)
    }
}