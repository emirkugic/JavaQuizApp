package com.example.quizapp.ui.screens

import QuizViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.quizapp.R

@Composable
fun MainMenuScreen(navController: NavController, viewModel: QuizViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { navController.navigate("user/${viewModel.username}/${viewModel.score}") }) {
                Icon(Icons.Default.AccountCircle, contentDescription = "User Profile")
            }
        }

        Column {
            Image(
                painterResource(R.drawable.logo),
                contentDescription = "Quiz Logo")
            Text(
                text = "Java Quiz App",
                color = Color.Red,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
        }
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { navController.navigate("quiz/easy") }
        ) {
            Text(text = "Easy")
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { navController.navigate("quiz/medium") }
        ) {
            Text(text = "Medium")
        }

        Button(
            modifier = Modifier.padding(16.dp),
            onClick = { navController.navigate("quiz/hard") }
        ) {
            Text(text = "Hard")
        }
    }
}


