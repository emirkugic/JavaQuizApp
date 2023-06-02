package com.example.quizapp.ui.screens

import QuizViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.ui.Screen

@Composable
fun UserScreen(navController: NavController, viewModel: QuizViewModel) {
    val username = viewModel.username ?: ""
    val easyScore by viewModel.easyScore.collectAsState()
    val mediumScore by viewModel.mediumScore.collectAsState()
    val hardScore by viewModel.hardScore.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "User Profile Picture",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Username: $username")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Easy Quiz Score: $easyScore/5")
        Text(text = "Medium Quiz Score: $mediumScore/5")
        Text(text = "Hard Quiz Score: $hardScore/5")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Handle logout here
                viewModel.logout()
                navController.navigate(Screen.Login.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Logout")
        }
    }
}
