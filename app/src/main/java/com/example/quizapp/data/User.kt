package com.example.quizapp.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val username: String,
    @NonNull
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "easy_score") val easyScore: Int = 0,
    @ColumnInfo(name = "medium_score") val mediumScore: Int = 0,
    @ColumnInfo(name = "hard_score") val hardScore: Int = 0
)

