package com.example.quizapp.ui.screens

import CustomTopAppBar
import QuizViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp.R
import com.example.quizapp.ui.Screen

@Composable
fun UserScreen(navController: NavController, viewModel: QuizViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        CustomTopAppBar(
            title = "Java Quiz App",
            icon = Icons.Default.Home,
            contentDescription = "Back",
            action = { navController.navigate(Screen.MainMenu.route) }
        )
        val username = viewModel.username ?: ""
        val easyScore by viewModel.easyScore.collectAsState()
        val mediumScore by viewModel.mediumScore.collectAsState()
        val hardScore by viewModel.hardScore.collectAsState()

        Column(
            modifier = Modifier
                .padding(top = 200.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "$username",
                fontSize = 35.sp,
                color = colorScheme.primary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(bottom = 20.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "User Profile Picture",
                modifier = Modifier
                    .width(190.dp)
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Your scores: ",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 20.dp),
                color = colorScheme.error,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )

            Row (modifier = Modifier
                .padding(top = 10.dp)
            ){
                Text(text = "Easy Quiz: ")
                Text(
                    text = "$easyScore / 5",
                    color = if (easyScore <= 2) Color.Red else Color.Green
                )
            }
            Row (modifier = Modifier
                .padding(top = 10.dp)
            ) {
                Text(text = "Medium Quiz: ")
                Text(
                    text = "$mediumScore / 5",
                    color = if (mediumScore <= 2) Color.Red else Color.Green
                )
            }
            Row (modifier = Modifier
                .padding(top = 10.dp)
            ) {
                Text(
                    text = "Hard Quiz: ")
                Text(
                    text = "$hardScore / 5",
                    color = if (hardScore <= 2) Color.Red else Color.Green
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route)
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    //.fillMaxWidth()
                    .padding(top = 180.dp)
                    .width(200.dp)
            ) {
                Text("Logout")
            }

        }
    }
}
