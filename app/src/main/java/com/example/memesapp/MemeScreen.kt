package com.example.memesapp

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.memesapp.download.AndroidDownloader

@Composable
fun MemeScreen(viewModel: MemeViewModel, modifier: Modifier,context:Context) {
    val memeData = viewModel.meme.observeAsState().value // Observe LiveData
    val downloader = AndroidDownloader(context)

    val downloaded = remember {
        mutableStateOf(false)
    }

    val count = remember { mutableStateOf(getRandomNumber(0,99)) } // Replace with desired index
    val index = count.value%99
    val imageUrl = memeData?.data?.memes?.get(index)?.url ?: "https://demofree.sirv.com/nope-not-here.jpg"
    val Imagename = memeData?.data?.memes?.get(index)?.name ?: "Error"

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp, vertical = 20.dp), contentAlignment = Alignment.Center) {
        Column {
            Text(
                text = Imagename,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            if (imageUrl.isNotEmpty()) { // Check if image URL is not empty
                AsyncImage(model = imageUrl, contentDescription = null)
            } else {
                Text(text = "Loading...") // Display placeholder while loading
            }
        }


        Button(
            onClick = {
                val x =  getRandomNumber(0, 99)
                count.value = x
                Log.d("MemeScreen", "count: ${count.value}")
                downloaded.value = !downloaded.value
            },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth(0.7f)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "New Meme",
            )
        }

        Button(
            onClick = {
                downloader.downloadFile(imageUrl, name = Imagename)
                downloaded.value = !downloaded.value
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .fillMaxWidth(0.25f)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            if(downloaded.value){
                Icon(painter = painterResource(id = R.drawable.baseline_file_download_done_24 ),
                    contentDescription = null)
            }else{
                Icon(painter = painterResource(id = R.drawable.baseline_download_24),
                    contentDescription = null)
            }
        }
    }
}

fun getRandomNumber(from: Int, to: Int): Int {
    if (from > to) {
        throw IllegalArgumentException("`from` must be less than or equal to `to`")
    }
    val range = to - from + 1  // Calculate the range (inclusive)
    return (Math.random() * range).toInt() + from  // Generate random number within range and offset by from
}

