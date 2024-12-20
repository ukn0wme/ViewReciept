package com.example.viewreciept.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_profile")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val surname: String,
    val favoriteKitchen: String,
    val favoriteMeal: String,
    val profilePicturePath: String // Path to the saved profile picture
)