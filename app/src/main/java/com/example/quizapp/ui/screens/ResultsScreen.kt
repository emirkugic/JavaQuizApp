package com.example.quizapp.ui.screens

import android.os.Build.VERSION.SDK_INT
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.quizapp.R
import com.example.quizapp.ui.Screen
import kotlin.random.Random

@Composable
fun ResultsScreen(navController: NavController, score: Int, totalQuestions: Int) {
    val isDarkMode = isSystemInDarkTheme()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Image(
            painter = painterResource(if (isDarkMode) R.drawable.results_light else R.drawable.results_dark),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Randomizer(score)
            Text(text = "Quiz Finished!")
            Text(text = "Your score: $score / $totalQuestions")
            Button(
                onClick = { navController.navigate(Screen.MainMenu.route) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(200.dp)
            ) {
                Text("Back to Main Menu")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun Randomizer(score: Int) {
    val randomNumber = Random.nextInt(1, 4)
    MaterialTheme {
        val gifRes = if (score >= 3) {
            when (randomNumber) {
                1 -> R.drawable.happygif1
                2 -> R.drawable.happygif2
                3 -> R.drawable.happygif3
                else -> R.drawable.happygif1
            }
        } else {
            when (randomNumber) {
                1 -> R.drawable.sadgif1
                2 -> R.drawable.sadgif2
                3 -> R.drawable.sadgif3
                4 -> R.drawable.sadgif4
                5 -> R.drawable.sadgif5
                else -> R.drawable.sadgif1
            }
        }
        loadGif(gifRes)
    }
}

@Composable
fun loadGif(gifRes: Int) {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context).data(data = gifRes).apply(block = {
                size(Size.ORIGINAL)
            }).build(), imageLoader = imageLoader
        ),
        contentDescription = null,
        modifier = Modifier.fillMaxWidth().width(150.dp).height(150.dp),
    )
}
