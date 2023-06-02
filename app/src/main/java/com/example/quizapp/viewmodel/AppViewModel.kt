package com.example.quizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.data.User
import com.example.quizapp.repository.AppRepository
import com.example.quizapp.ui.Screen
import kotlinx.coroutines.launch

class AppViewModel(private val repository: AppRepository) : ViewModel() {

    // User-related operations
    fun registerUser(username: String, password: String) {
        val user = User(username, password)
        viewModelScope.launch {
            repository.insertUser(user)
        }
    }

    fun loginUser(username: String, password: String) = viewModelScope.launch {
        val user = repository.loginUser(username, password)
        // Handle user login here
    }
}
