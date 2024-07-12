package com.example.memesapp

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MemeScreen(viewModel: MemeViewModel, modifier: Modifier) {
    val memeData = viewModel.meme.observeAsState().value // Observe LiveData

    val count = remember { mutableStateOf(getRandomNumber(0,99)) } // Replace with desired index
    val imageUrl = memeData?.data?.memes?.get(count.value%99)?.url ?: ""
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (imageUrl.isNotEmpty()) { // Check if image URL is not empty
            AsyncImage(model = imageUrl, contentDescription = null)
        } else {
            Text(text = "Loading...") // Display placeholder while loading
        }
        Button(
            onClick = {
                var x =  getRandomNumber(0, 99)
                count.value = x
                Log.d("MemeScreen", "count: ${count.value}")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(80.dp)
                .padding(bottom = 36.dp)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                text = "New Meme",
            )
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