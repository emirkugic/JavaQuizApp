package com.example.quizapp.repository

import com.example.quizapp.data.User
import com.example.quizapp.data.UserDao
import com.example.quizapp.ui.Screen


class AppRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.register(user)
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDao.getUser(username)
    }
}
