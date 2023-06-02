package com.example.quizapp

import QuizViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.data.AppDatabase
import com.example.quizapp.ui.AppNavHost
import com.example.quizapp.ui.theme.QuizAppTheme
import com.example.quizapp.viewmodel.QuizViewModelFactory


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizAppTheme {
                val navController = rememberNavController()
                val userDao = AppDatabase.getDatabase(this).userDao()
                val viewModelFactory = QuizViewModelFactory(userDao)
                val viewModel = ViewModelProvider(this, viewModelFactory).get(QuizViewModel::class.java)
                AppNavHost(navController, viewModel)
            }
        }
    }
}


